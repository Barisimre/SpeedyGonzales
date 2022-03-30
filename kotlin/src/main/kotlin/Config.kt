

object Config {
    val rpc = "http://130.89.172.197:8545"
    val wsrpc = "ws://130.89.172.197:8546"
    val v2router = "0x1421bde4b10e8dd459b3bcb598810b1337d56842"
    val v2method = "0x38ed1739"

    val tokens = mapOf (
        "471ece3750da237f93b8e339c536989b8978a438" to Currency.CELO,
        "765de816845861e75a25fca122bb6898b8b1282a" to Currency.CUSD,
        "d8763cba276a3738e6de85b4b3bf5fded6d6ca73" to Currency.CEUR,
        "122013fd7df1c6f636a5bb8f03108e876548b455" to Currency.WETH,
        "29dfce9c22003a4999930382fd00f9fd6133acd1" to Currency.SUSHI,
    )


}