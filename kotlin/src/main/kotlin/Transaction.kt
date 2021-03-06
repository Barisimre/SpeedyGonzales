import java.math.BigInteger

data class BaseTransaction(val from: String, val to: String, val input: String, val gasPrice: String, val hash: String, val gasAmount: String) {
    val method
        get() = input.substring(0, 10)
}

class Transaction(transaction: BaseTransaction) {
    var input = transaction.input
    val contract = transaction.to
    val from = transaction.from
    val gasPrice = transaction.gasPrice
    val gasAmount = transaction.gasAmount


    val method
        get() = input.substring(0, 10)

    val body
        get() = input.substring(10)

    val amountIn
        get() = BigInteger(body.substring(0*64, 1*64), 16)

    val minAmountOut
        get() = BigInteger(body.substring(1*64, 2*64), 16)

    val to
        get() = BigInteger(body.substring(2*64, 3*64), 16)

    val deadline
        get() = BigInteger(body.substring(3*64, 4*64), 16)

    val path
        get() = body.substring(startIndex = 6*64).chunked(64).map {"0x" + it.toBigInteger(16).toString(16).padStart(40, '0')}

    fun replaceTo(to: String) {
        if (to.length > 64) throw Exception("Address $to does not have 64 characters")
        val inputString = to.padStart(64, '0')
        input = input.substring(0, 10 + 3*64) + inputString + input.substring(10 + 4*64)
    }

    fun replaceAmountIn(amountIn: BigInteger) {
        val inputString = amountIn.toString(16).padStart(64, '0')
        input = input.substring(0, 10) + inputString + input.substring(10 + 64)
    }

    fun replaceAmountOut(minAmountOut: BigInteger) {
        val inputString = minAmountOut.toString(16).padStart(64, '0')
        input = input.substring(0, 10 + 64) + inputString + input.substring(10 + 2*64)
    }

    fun getPrettyInputString(): String {
        var res = input.substring(0, 10)

        for (i in 10 until input.length step 64) {
            res += "\n${input.substring(i, i+64)}"
        }
        return res
    }
}