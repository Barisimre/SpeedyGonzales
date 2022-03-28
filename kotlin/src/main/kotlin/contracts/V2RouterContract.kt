package connectors.uniswapv2

import org.web3j.protocol.Web3j
import org.web3j.abi.TypeReference
import org.web3j.abi.Utils
import org.web3j.abi.datatypes.*
import org.web3j.abi.datatypes.Function
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.abi.datatypes.generated.Uint8
import org.web3j.abi.datatypes.generated.Bytes32
import org.web3j.crypto.Credentials
import org.web3j.tx.Contract
import org.web3j.tx.TransactionManager
import java.math.BigInteger
import java.util.*

/**
 *
 * Auto generated code.
 *
 * **Do not modify!**
 *
 * Please use the [web3j command line tools](https://docs.web3j.io/command_line.html),
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * [codegen module](https://github.com/web3j/web3j/tree/master/codegen) to update.
 *
 *
 * Generated with web3j version 1.4.1.
 */
class V2RouterContract : Contract {
    @Deprecated("")
    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        gasPrice: BigInteger?,
        gasLimit: BigInteger?
    ) : super(
        BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        BINARY, contractAddress, web3j, credentials, contractGasProvider
    ) {
    }

    @Deprecated("")
    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        transactionManager: TransactionManager?,
        gasPrice: BigInteger?,
        gasLimit: BigInteger?
    ) : super(
        BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        transactionManager: TransactionManager?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        BINARY, contractAddress, web3j, transactionManager, contractGasProvider
    ) {
    }

    fun WAVAX(): RemoteFunctionCall<String> {
        val function = Function(
            FUNC_WAVAX,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun addLiquidity(
        tokenA: String?,
        tokenB: String?,
        amountADesired: BigInteger?,
        amountBDesired: BigInteger?,
        amountAMin: BigInteger?,
        amountBMin: BigInteger?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_ADDLIQUIDITY,
            Arrays.asList<Type<*>>(
                Address(160, tokenA),
                Address(160, tokenB),
                Uint256(amountADesired),
                Uint256(amountBDesired),
                Uint256(amountAMin),
                Uint256(amountBMin),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun addLiquidityAVAX(
        token: String?,
        amountTokenDesired: BigInteger?,
        amountTokenMin: BigInteger?,
        amountAVAXMin: BigInteger?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_ADDLIQUIDITYAVAX,
            Arrays.asList<Type<*>>(
                Address(160, token),
                Uint256(amountTokenDesired),
                Uint256(amountTokenMin),
                Uint256(amountAVAXMin),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun factory(): RemoteFunctionCall<String> {
        val function = Function(
            FUNC_FACTORY,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun getAmountIn(
        amountOut: BigInteger?,
        reserveIn: BigInteger?,
        reserveOut: BigInteger?
    ): RemoteFunctionCall<BigInteger> {
        val function = Function(
            FUNC_GETAMOUNTIN,
            Arrays.asList<Type<*>>(
                Uint256(amountOut),
                Uint256(reserveIn),
                Uint256(reserveOut)
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun getAmountOut(
        amountIn: BigInteger?,
        reserveIn: BigInteger?,
        reserveOut: BigInteger?
    ): RemoteFunctionCall<BigInteger> {
        val function = Function(
            FUNC_GETAMOUNTOUT,
            Arrays.asList<Type<*>>(
                Uint256(amountIn),
                Uint256(reserveIn),
                Uint256(reserveOut)
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun getAmountsIn(amountOut: BigInteger?, path: List<String>?): RemoteFunctionCall<List<*>> {
        val function = Function(
            FUNC_GETAMOUNTSIN,
            listOf(
                Uint256(amountOut),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                )
            ),
            listOf<TypeReference<*>>(object : TypeReference<DynamicArray<Uint256?>?>() {})
        )
        return RemoteFunctionCall(
            function
        ) {
            val result =
                executeCallSingleValueReturn<Type<*>, List<*>>(function, List::class.java) as List<Type<*>>
            convertToNative<Type<*>, Any>(result)
        }
    }

    fun getAmountsOut(amountIn: BigInteger?, path: List<String>?): RemoteFunctionCall<List<*>> {
        val function = Function(
            FUNC_GETAMOUNTSOUT,
            Arrays.asList(
                Uint256(amountIn),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                )
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<DynamicArray<Uint256?>?>() {})
        )
        return RemoteFunctionCall(
            function
        ) {
            val result =
                executeCallSingleValueReturn<Type<*>, List<*>>(function, List::class.java) as List<Type<*>>
            convertToNative<Type<*>, Any>(result)
        }
    }

    fun quote(amountA: BigInteger?, reserveA: BigInteger?, reserveB: BigInteger?): RemoteFunctionCall<BigInteger> {
        val function = Function(
            FUNC_QUOTE,
            Arrays.asList<Type<*>>(
                Uint256(amountA),
                Uint256(reserveA),
                Uint256(reserveB)
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun removeLiquidity(
        tokenA: String?,
        tokenB: String?,
        liquidity: BigInteger?,
        amountAMin: BigInteger?,
        amountBMin: BigInteger?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITY,
            Arrays.asList<Type<*>>(
                Address(160, tokenA),
                Address(160, tokenB),
                Uint256(liquidity),
                Uint256(amountAMin),
                Uint256(amountBMin),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun removeLiquidityAVAX(
        token: String?,
        liquidity: BigInteger?,
        amountTokenMin: BigInteger?,
        amountAVAXMin: BigInteger?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITYAVAX,
            Arrays.asList<Type<*>>(
                Address(160, token),
                Uint256(liquidity),
                Uint256(amountTokenMin),
                Uint256(amountAVAXMin),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun removeLiquidityAVAXSupportingFeeOnTransferTokens(
        token: String?,
        liquidity: BigInteger?,
        amountTokenMin: BigInteger?,
        amountAVAXMin: BigInteger?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITYAVAXSUPPORTINGFEEONTRANSFERTOKENS,
            Arrays.asList<Type<*>>(
                Address(160, token),
                Uint256(liquidity),
                Uint256(amountTokenMin),
                Uint256(amountAVAXMin),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun removeLiquidityAVAXWithPermit(
        token: String?,
        liquidity: BigInteger?,
        amountTokenMin: BigInteger?,
        amountAVAXMin: BigInteger?,
        to: String?,
        deadline: BigInteger?,
        approveMax: Boolean?,
        v: BigInteger?,
        r: ByteArray?,
        s: ByteArray?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITYAVAXWITHPERMIT,
            Arrays.asList<Type<*>>(
                Address(160, token),
                Uint256(liquidity),
                Uint256(amountTokenMin),
                Uint256(amountAVAXMin),
                Address(160, to),
                Uint256(deadline),
                Bool(approveMax),
                Uint8(v),
                Bytes32(r),
                Bytes32(s)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun removeLiquidityAVAXWithPermitSupportingFeeOnTransferTokens(
        token: String?,
        liquidity: BigInteger?,
        amountTokenMin: BigInteger?,
        amountAVAXMin: BigInteger?,
        to: String?,
        deadline: BigInteger?,
        approveMax: Boolean?,
        v: BigInteger?,
        r: ByteArray?,
        s: ByteArray?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITYAVAXWITHPERMITSUPPORTINGFEEONTRANSFERTOKENS,
            Arrays.asList<Type<*>>(
                Address(160, token),
                Uint256(liquidity),
                Uint256(amountTokenMin),
                Uint256(amountAVAXMin),
                Address(160, to),
                Uint256(deadline),
                Bool(approveMax),
                Uint8(v),
                Bytes32(r),
                Bytes32(s)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun removeLiquidityWithPermit(
        tokenA: String?,
        tokenB: String?,
        liquidity: BigInteger?,
        amountAMin: BigInteger?,
        amountBMin: BigInteger?,
        to: String?,
        deadline: BigInteger?,
        approveMax: Boolean?,
        v: BigInteger?,
        r: ByteArray?,
        s: ByteArray?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_REMOVELIQUIDITYWITHPERMIT,
            Arrays.asList<Type<*>>(
                Address(160, tokenA),
                Address(160, tokenB),
                Uint256(liquidity),
                Uint256(amountAMin),
                Uint256(amountBMin),
                Address(160, to),
                Uint256(deadline),
                Bool(approveMax),
                Uint8(v),
                Bytes32(r),
                Bytes32(s)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapAVAXForExactTokens(
        amountOut: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPAVAXFOREXACTTOKENS,
            Arrays.asList(
                Uint256(amountOut),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactAVAXForTokens(
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTAVAXFORTOKENS,
            Arrays.asList(
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactAVAXForTokensSupportingFeeOnTransferTokens(
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTAVAXFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS,
            Arrays.asList(
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactTokensForAVAX(
        amountIn: BigInteger?,
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTTOKENSFORAVAX,
            Arrays.asList(
                Uint256(amountIn),
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactTokensForAVAXSupportingFeeOnTransferTokens(
        amountIn: BigInteger?,
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTTOKENSFORAVAXSUPPORTINGFEEONTRANSFERTOKENS,
            Arrays.asList(
                Uint256(amountIn),
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactTokensForTokens(
        amountIn: BigInteger?,
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTTOKENSFORTOKENS,
            Arrays.asList(
                Uint256(amountIn),
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapExactTokensForTokensSupportingFeeOnTransferTokens(
        amountIn: BigInteger?,
        amountOutMin: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPEXACTTOKENSFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS,
            Arrays.asList(
                Uint256(amountIn),
                Uint256(amountOutMin),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapTokensForExactAVAX(
        amountOut: BigInteger?,
        amountInMax: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPTOKENSFOREXACTAVAX,
            Arrays.asList(
                Uint256(amountOut),
                Uint256(amountInMax),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swapTokensForExactTokens(
        amountOut: BigInteger?,
        amountInMax: BigInteger?,
        path: List<String>?,
        to: String?,
        deadline: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SWAPTOKENSFOREXACTTOKENS,
            Arrays.asList(
                Uint256(amountOut),
                Uint256(amountInMax),
                DynamicArray(
                    Address::class.java,
                    Utils.typeMap(path, Address::class.java)
                ),
                Address(160, to),
                Uint256(deadline)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    companion object {
        const val BINARY = "Bin file was not provided"
        const val FUNC_WAVAX = "WAVAX"
        const val FUNC_ADDLIQUIDITY = "addLiquidity"
        const val FUNC_ADDLIQUIDITYAVAX = "addLiquidityAVAX"
        const val FUNC_FACTORY = "factory"
        const val FUNC_GETAMOUNTIN = "getAmountIn"
        const val FUNC_GETAMOUNTOUT = "getAmountOut"
        const val FUNC_GETAMOUNTSIN = "getAmountsIn"
        const val FUNC_GETAMOUNTSOUT = "getAmountsOut"
        const val FUNC_QUOTE = "quote"
        const val FUNC_REMOVELIQUIDITY = "removeLiquidity"
        const val FUNC_REMOVELIQUIDITYAVAX = "removeLiquidityAVAX"
        const val FUNC_REMOVELIQUIDITYAVAXSUPPORTINGFEEONTRANSFERTOKENS =
            "removeLiquidityAVAXSupportingFeeOnTransferTokens"
        const val FUNC_REMOVELIQUIDITYAVAXWITHPERMIT = "removeLiquidityAVAXWithPermit"
        const val FUNC_REMOVELIQUIDITYAVAXWITHPERMITSUPPORTINGFEEONTRANSFERTOKENS =
            "removeLiquidityAVAXWithPermitSupportingFeeOnTransferTokens"
        const val FUNC_REMOVELIQUIDITYWITHPERMIT = "removeLiquidityWithPermit"
        const val FUNC_SWAPAVAXFOREXACTTOKENS = "swapAVAXForExactTokens"
        const val FUNC_SWAPEXACTAVAXFORTOKENS = "swapExactAVAXForTokens"
        const val FUNC_SWAPEXACTAVAXFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS =
            "swapExactAVAXForTokensSupportingFeeOnTransferTokens"
        const val FUNC_SWAPEXACTTOKENSFORAVAX = "swapExactTokensForAVAX"
        const val FUNC_SWAPEXACTTOKENSFORAVAXSUPPORTINGFEEONTRANSFERTOKENS =
            "swapExactTokensForAVAXSupportingFeeOnTransferTokens"
        const val FUNC_SWAPEXACTTOKENSFORTOKENS = "swapExactTokensForTokens"
        const val FUNC_SWAPEXACTTOKENSFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS =
            "swapExactTokensForTokensSupportingFeeOnTransferTokens"
        const val FUNC_SWAPTOKENSFOREXACTAVAX = "swapTokensForExactAVAX"
        const val FUNC_SWAPTOKENSFOREXACTTOKENS = "swapTokensForExactTokens"
        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): V2RouterContract {
            return V2RouterContract(contractAddress, web3j, credentials, gasPrice, gasLimit)
        }

        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): V2RouterContract {
            return V2RouterContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            contractGasProvider: ContractGasProvider?
        ): V2RouterContract {
            return V2RouterContract(contractAddress, web3j, credentials, contractGasProvider)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            contractGasProvider: ContractGasProvider?
        ): V2RouterContract {
            return V2RouterContract(contractAddress, web3j, transactionManager, contractGasProvider)
        }
    }
}