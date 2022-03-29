import java.math.BigInteger

data class Transaction(val from: String, val contract: String, val input: String, val gasPrice: String) {
    val method
        get() = input.substring(0, 10)

    val body = input.substring(10)

    val amountIn
        get() = BigInteger(body.substring(0*64, 1*64), 16)

    val minAmountOut
        get() = BigInteger(body.substring(1*64, 2*64), 16)

    val to
        get() = BigInteger(body.substring(2*64, 3*64), 16)

    val deadline
        get() = BigInteger(body.substring(3*64, 4*64), 16)

    val path = body.substring(startIndex = 6*64).chunked(64).map { BigInteger(it, 16) }
}