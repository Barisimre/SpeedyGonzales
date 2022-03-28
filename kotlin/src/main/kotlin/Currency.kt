enum class Currency {
    CELO,
}

fun String.toCurrency(): Currency {
    return Currency.valueOf(this)
}