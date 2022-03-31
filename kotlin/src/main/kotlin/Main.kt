import io.github.cdimascio.dotenv.Dotenv
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService


fun main(args: Array<String>) {
    Dotenv.configure().systemProperties().load()

    val httpService = HttpService(Config.rpc)
    val web3j = Web3j.build(httpService)
    
    println("Started the bot.")

    val watcher = Watcher(web3j, WebSocketService(Config.wsrpc, false))
    watcher.run()


    




}