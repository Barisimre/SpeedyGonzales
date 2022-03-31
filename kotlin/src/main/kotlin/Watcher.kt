
import org.web3j.contracts.eip20.generated.ERC20
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.core.methods.response.EthSubscribe
import org.web3j.protocol.http.HttpService

import org.web3j.protocol.websocket.WebSocketService
import org.web3j.protocol.websocket.events.NewHeadsNotification
import org.web3j.protocol.websocket.events.PendingTransactionNotification
import org.web3j.tx.FastRawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import org.web3j.tx.response.PollingTransactionReceiptProcessor
import org.web3j.utils.Numeric
import java.math.BigInteger
import java.time.Instant


class Watcher(private val web3j: Web3j, private val webSocketService: WebSocketService) {
    val httpService = HttpService(Config.rpc)


    private val privateKey = System.getProperty("CELO_PRIVATE_KEY")
    private val credentials = Credentials.create(privateKey)
    private val gasProvider = StaticGasProvider(BigInteger.valueOf(4_100_000_000L), BigInteger.valueOf(800_000)) // gasPrice, gasLimit
    private val transactionManager = FastRawTransactionManager(web3j, credentials, PollingTransactionReceiptProcessor(web3j,100, 150))
    val ERC20Tokens = Config.tokens.map { it.key to ERC20.load(it.key, web3j, transactionManager, gasProvider) }.toMap()

    private fun subscribeBlocks() {
        val request = Request("eth_subscribe", listOf("newHeads"), webSocketService, EthSubscribe::class.java)
        val events = webSocketService.subscribe(request, "eth_unsubscribe", NewHeadsNotification::class.java)
        events.subscribe { event ->
            val hexNumber = Numeric.hexStringToByteArray(event.params.result.number)
            println(BigInteger(hexNumber))
        }
    }

    private fun subscribeTransactions() {
        val request = Request("eth_subscribe", listOf("newPendingTransactions"), webSocketService, EthSubscribe::class.java)
        val events = webSocketService.subscribe(request, "eth_unsubscribe", PendingTransactionNotification::class.java)
        events.subscribe { event ->
            processTransaction(event.params.result)
//            val hexNumber = Numeric.hexStringToByteArray(event.params.result.number)
//            println(BigInteger(hexNumber))
        }
    }

    private fun processTransaction(hash: String) {
        val newTransactionTiming = Instant.now()
        val request = Request("txpool_content", emptyList<String>(),  webSocketService, EthCall::class.java)
        val result = httpService.send(request, Response::class.java)
        val details = result.getTransactionDetails(hash)
        for (transaction in details) {

            if (transaction.from !in Config.allowedTargets) continue
            println("hash: ${transaction.hash} to: ${transaction.to}")

//            Try to emulate the transaction


            if (transaction.to.lowercase() in Config.v2routers) {
                // Bingo, a sushi-swap transaction
                if (transaction.method != Config.v2method) continue
                println("new transaction hash came in at: $newTransactionTiming")
                println("Right method")
                frontRunTransaction(Transaction(transaction))

            }
        }
    }

    private fun frontRunTransaction(transaction: Transaction) {
        println("Starting frontrun: ${Instant.now()}")
        val inToken = transaction.path.first()
        val erc20 = ERC20Tokens["0x" + inToken.toString(16)] ?: throw Exception("Value was null for token: 0x${inToken.toString(16)}")
        println(Config.address)
        val ownInBalance = erc20.balanceOf(Config.address).send() / 2.toBigInteger()
        println("Path in: ${Config.tokens["0x" + inToken.toString(16)]}, amount: $ownInBalance, \n${transaction.path.map { Config.tokens["0x" + it.toString(16)] }}")

        if (ownInBalance <= 0.toBigInteger()) return

        transaction.replaceTo(Config.address.substring(2))
        transaction.replaceAmountIn(ownInBalance)

        println(transaction.gasPrice)
        val gasPrice = transaction.gasPrice.substring(2).toBigInteger(16) + 0.toBigInteger()

        val trans = org.web3j.protocol.core.methods.request.Transaction(
            Config.address,
            null,
            gasPrice,
            null,
            transaction.contract,
            0.toBigInteger(),
            transaction.input,
        )

        println("input string: ${transaction.input}")

        println("executing ethCall: ${Instant.now()}")
        val call = web3j.ethCall(trans, DefaultBlockParameter.valueOf("latest"))
        println("call: ${call.method}, ${call.params.map {it.toString()}}")
        val result = call.send()

        println(result.error)
    }

    fun run() {
        webSocketService.connect()
        subscribeTransactions()
    }

    fun Response<*>.getTransactionDetails(transactionHash: String): List<BaseTransaction> {
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
                    hash = transactionData["hash"] as String
                ))
            } catch (e: Exception) {
//                println(transactionData)
//                println(e)
//                e.printStackTrace()
                continue
            }
        }
        return result
    }

}