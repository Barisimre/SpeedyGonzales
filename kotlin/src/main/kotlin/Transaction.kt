import java.math.BigInteger

data class Transaction(val from: String, val contract: String, val input: String, val gasPrice: String) {
    val method
        get() = input.substring(0, 10)

    val amountIn
        get() = BigInteger(input.substring(10 + 0*64, 10 + 1*64))

    val minAmountOut
        get() = BigInteger(input.substring(10 + 1*64, 10 + 2*64))

    val to
        get() = BigInteger(input.substring(10 + 2*64, 10 + 3*64))

    val deadline
        get() = BigInteger(input.substring(10 + 3*64, 10 + 4*64))

    val path = input.substring(startIndex = 10 + 4*64).chunked(64).map { BigInteger(it) }
}