import connectors.uniswapv2.V2RouterContract
import io.github.cdimascio.dotenv.Dotenv
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.FastRawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import org.web3j.tx.response.PollingTransactionReceiptProcessor
import java.math.BigInteger

fun main(args: Array<String>) {
    Dotenv.configure().systemProperties().load()
    val privateKey = System.getProperty("CELO_PRIVATE_KEY")

    val httpService = HttpService(Config.rpc)
    val web3j = Web3j.build(httpService)
    val credentials = Credentials.create(privateKey)
    val gasProvider = StaticGasProvider(BigInteger.valueOf(4_100_000_000L), BigInteger.valueOf(800_000)) // gasPrice, gasLimit
    val transactionManager = FastRawTransactionManager(web3j, credentials, PollingTransactionReceiptProcessor(web3j,100, 150))

    val v2router = V2RouterContract.load(Config.v2router, web3j, credentials, gasProvider)
    
    println("web3j block number: ${web3j.ethBlockNumber()}")

    val watcher = Watcher(web3j, WebSocketService(Config.wsrpc, false))
    watcher.run()


    




}