

object Config {
    const val rpc = "http://130.89.172.197:8545"
    const val wsrpc = "ws://130.89.172.197:8546"
    const val ourAddress = "0xD6A14bF0e77429b84F17eB7cB1ccBDc062Dfc2c1"
    val v2routers = listOf(
        "0x1421bde4b10e8dd459b3bcb598810b1337d56842",
        "0xE3D8bd6Aed4F159bc8000a9cD47CffDb95F96121",
    ).map {it.lowercase()}

    const val v2method = "0x38ed1739"

    val tokens = mapOf (
        "0x471ece3750da237f93b8e339c536989b8978a438" to Currency.CELO,
        "0x765de816845861e75a25fca122bb6898b8b1282a" to Currency.CUSD,
        "0x918146359264C492BD6934071c6Bd31C854EDBc3" to Currency.mCUSD,
        "0xE273Ad7ee11dCfAA87383aD5977EE1504aC07568" to Currency.mCEUR,
        "0x218c3c3D49d0E7B37aff0D8bB079de36Ae61A4c0" to Currency.FTM,
        "0x8E3670FD7B0935d3FE832711deBFE13BB689b690" to Currency.AVAX,
        "0x90Ca507a5D4458a4C6C6249d186b6dCb02a5BCCd" to Currency.DAI,
        "0x173234922eB27d5138c5e481be9dF5261fAeD450" to Currency.SOL,
        "0xd8763cba276a3738e6de85b4b3bf5fded6d6ca73" to Currency.CEUR,
        "0x122013fd7df1c6f636a5bb8f03108e876548b455" to Currency.WETH,
        "0x29dfce9c22003a4999930382fd00f9fd6133acd1" to Currency.SUSHI,
        "0xA649325Aa7C5093d12D6F98EB4378deAe68CE23F" to Currency.BNB,
        "0x88eec49252c8cbc039dcdb394c0c2ba2f1637ea0" to Currency.USDT,
    ).map { it.key.lowercase() to it.value }.toMap()

    val allowedTargets = listOf(
        "0x9F034Dddd9c6464E827f648110dda4b6A3A42844",
        "0xc7D252fdFAee7f3C06Ce676DcF68D0AA368DDc49",
        "0x432963a481e1aa7f09e9ea878d4a596eee6eb63b",
    ).map { it.lowercase() }


}