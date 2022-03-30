enum class Currency {
    CELO,
    CUSD,
    CEUR,
    WETH,
    SUSHI,
}

fun String.toCurrency(): Currency {
    return Currency.valueOf(this)
}