private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    Dotenv.configure().systemProperties().load()
    val privateKey = System.getProperty("CELO_PRIVATE_KEY")

    val web3j = Web3j.build(HttpService(config.rpc))
    val credentials = Credentials.create(privateKey)
    val gasProvider = StaticGasProvider(BigInteger.valueOf(4_100_000_000L), BigInteger.valueOf(800_000)) // gasPrice, gasLimit
    val transactionManager = FastRawTransactionManager(web3j, credentials, PollingTransactionReceiptProcessor(web3j,100, 150))

    val v2router = V2RouterContract.load(config.v2router, web3j, credentials, gasProvider)
    
    logger.info {"web3j block number: ${web3j.eth.blockNumber()}"}
    




}