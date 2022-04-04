enum class Currency {
    CELO,
    CUSD,
    CEUR,
    WETH,
    SUSHI,
    mCUSD,
    mCEUR,
    FTM,
    AVAX,
    DAI,
    SOL,
    BNB,
    USDT,
    WBTC,

}

fun String.toCurrency(): Currency {
    return Currency.valueOf(this)
}