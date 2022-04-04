import org.web3j.contracts.eip20.generated.ERC20
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.core.methods.response.EthSubscribe
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.protocol.websocket.events.PendingTransactionNotification
import org.web3j.tx.FastRawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import org.web3j.tx.response.PollingTransactionReceiptProcessor
import java.math.BigInteger
import java.time.Instant

class Watcher(private val web3j: Web3j, private val webSocketService: WebSocketService) {

    private val httpService = HttpService(Config.rpc)
    private val privateKey = System.getProperty("CELO_PRIVATE_KEY")
    private val credentials = Credentials.create(privateKey)
    private val gasProvider = StaticGasProvider(BigInteger.valueOf(4_100_000_000L), BigInteger.valueOf(800_000)) // gasPrice, gasLimit
    private val transactionManager = FastRawTransactionManager(web3j, credentials, PollingTransactionReceiptProcessor(web3j,100, 150))
    private val ERC20Tokens = Config.tokens.map { it.key to ERC20.load(it.key, web3j, transactionManager, gasProvider) }.toMap()
    private val totalBudgetRatio = 1.toBigInteger()


    private fun subscribeTransactions() {
        val request = Request("eth_subscribe", listOf("newPendingTransactions"), webSocketService, EthSubscribe::class.java)
        val events = webSocketService.subscribe(request, "eth_unsubscribe", PendingTransactionNotification::class.java)
        events.subscribe { event ->
            processTransaction(event.params.result)
        }
    }

    private fun approveTokens() {
        for (token in ERC20Tokens.values) {
            for (router in Config.v2routers) {
                if (token.allowance(Config.ourAddress, router).send() < 2.toBigInteger().pow(250)) {
                    token.approve(router, 2.toBigInteger().pow(251)).send()
                }
            }
        }
    }


    private fun processTransaction(hash: String) {

        val newTransactionTiming = Instant.now()
        val request = Request("txpool_content", emptyList<String>(),  webSocketService, EthCall::class.java)
        val result = httpService.send(request, Response::class.java)
        val details = result.getTransactionDetails(hash)
        for (transaction in details) {

            // Get rid of transactions that are not by the target addresses
            if (transaction.from !in Config.allowedTargets) continue

            if (transaction.to.lowercase() in Config.v2routers) {
                // Bingo, a sushi-swap transaction

                // Different method in the contract, we pass
                if (transaction.method != Config.v2method) continue

                println("--------------------------------------------------------------")
                println("Transaction with the right method at $newTransactionTiming.\n" +
                        "\t Hash: ${transaction.hash}\n" +
                        "\t By: ${transaction.from}\n" +
                        "\t To: ${transaction.to}\n")

                frontRunTransaction(Transaction(transaction))
            }
        }
    }


    private fun frontRunTransaction(transaction: Transaction) {
        println("\tStarting frontrun: ${Instant.now()}\n")
        val inToken = transaction.path.first()

        val erc20 = ERC20Tokens[inToken]
        if (erc20 == null) {
            println("Unknown input token, cancelling: ${inToken}")
            return
        }

        var ownInBalance = minOf(erc20.balanceOf(Config.ourAddress).send() / totalBudgetRatio, transaction.amountIn)


        println("\t Path in: ${Config.tokens[inToken]}," +
                " ${transaction.path.map { Config.tokens[it] }}")

        if (Config.tokens[inToken] == Currency.CELO) {
            ownInBalance = (ownInBalance * 7.toBigInteger()) / 10.toBigInteger()
            if (ownInBalance < 10.toBigInteger().pow(18)) {
                println("Trying to trade amount less then 1 celo, cancelling")
                return
            }
        }

        val minOut = (999.toBigInteger() * ownInBalance * transaction.minAmountOut) / (transaction.amountIn * 1000.toBigInteger())

        println("\t Our in: $ownInBalance, our our: $minOut\n" +
                "\t Their in: ${transaction.amountIn}, their out: ${transaction.minAmountOut}\n")


        if (Config.tokens[transaction.path.last()] == null) {
            println("Cancelling because of unknown out token: ${transaction.path.last()}")
            return
        }

        if (ownInBalance <= 0.toBigInteger()) {
            println("We do not own this token.")
            return
        }

        println("\t Their input string: ${transaction.getPrettyInputString()}")

        transaction.replaceTo(Config.ourAddress.substring(2))
        transaction.replaceAmountIn(ownInBalance)
        transaction.replaceAmountOut(minOut)

        if (minOut <= 0.toBigInteger()) {
            println("Minout is zero")
            return
        }

        println("\t Transaction gas price: ${transaction.gasPrice}")
        val gasPrice = transaction.gasPrice.substring(2).toBigInteger(16) + 1.toBigInteger()
        val gasAmount = transaction.gasAmount.substring(2).toBigInteger(16) * 2.toBigInteger()

        println("\t Our input string: ${transaction.getPrettyInputString()}")
        println("...")
        println("Executing Eth Call: ${Instant.now()}")

        val result = transactionManager.sendTransaction(gasPrice,
            gasAmount,
            transaction.contract,
            transaction.input,
            null)

        println("Result message: ${result.transactionHash}")
        println("")
    }


    fun run() {
        webSocketService.connect()
        approveTokens()
        subscribeTransactions()
    }


    private fun Response<*>.getTransactionDetails(transactionHash: String): List<BaseTransaction> {
        val pending = (this.result as LinkedHashMap<*, *>)["pending"]
        val result = ArrayList<BaseTransaction>()
        for (pendingTransaction in (pending as LinkedHashMap<*, *>).values) {
            val transactionData = (pendingTransaction as LinkedHashMap<*, *>).values.first() as LinkedHashMap<*, *>
            if (transactionData["hash"] != transactionHash) continue
            try {
                result.add(BaseTransaction(
                    from = transactionData["from"] as String,
                    to = transactionData["to"] as String,
                    input = transactionData["input"] as String,
                    gasPrice = transactionData["gasPrice"] as String,
                    gasAmount = transactionData["gas"] as String,
                    hash = transactionData["hash"] as String
                ))
            } catch (e: Exception) {
                continue
            }
        }
        return result
    }

}



