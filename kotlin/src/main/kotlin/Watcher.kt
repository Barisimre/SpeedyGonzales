
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.core.methods.response.EthSubscribe
import org.web3j.protocol.http.HttpService

import org.web3j.protocol.websocket.WebSocketService
import org.web3j.protocol.websocket.events.NewHeadsNotification
import org.web3j.protocol.websocket.events.PendingTransactionNotification
import org.web3j.utils.Numeric
import java.math.BigInteger


class Watcher(private val web3j: Web3j, private val webSocketService: WebSocketService) {
    val httpService = HttpService(Config.rpc)

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
        val request = Request("txpool_content", emptyList<String>(),  webSocketService, EthCall::class.java)
        val result = httpService.send(request, Response::class.java)
        val details = result.getTransactionDetails(hash)
        if (details != null && details.contract == Config.v2router) {
            // Bingo, a sushi-swap transaction
            println("bingo: ${details.method}, ${Config.v2method}")

            if (details.method == Config.v2method) return
            val path = details.path
            if (path.first() == path.last() && details.amountIn < details.minAmountOut) {
                println("Profitable trade found!!!!! hash: $hash:\n $details")
            }
        }
    }

    fun run() {
        webSocketService.connect()
        subscribeTransactions()
    }

    fun Response<*>.getTransactionDetails(transaction: String): Transaction? {
        val pending = (this.result as LinkedHashMap<*, *>)["pending"]

        for (pendingTransaction in (pending as LinkedHashMap<*, *>).values) {
            val transactionData = (pendingTransaction as LinkedHashMap<*, *>).values.first() as LinkedHashMap<*, *>
            try {
                if ((transactionData["hash"] as String) != transaction) continue
                return Transaction(
                    from = transactionData["from"] as String,
                    contract = transactionData["to"] as String,
                    input = transactionData["input"] as String,
                    gasPrice = transactionData["gasPrice"] as String
                )
            } catch (e: Exception) {
                continue
            }
        }
        return null
    }

}