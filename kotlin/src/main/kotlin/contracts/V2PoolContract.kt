package connectors.uniswapv2

import org.web3j.protocol.Web3j
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.abi.EventEncoder
import io.reactivex.Flowable
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.*
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.abi.datatypes.generated.Bytes32
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.abi.datatypes.generated.Uint8
import org.web3j.abi.datatypes.generated.Uint112
import org.web3j.abi.datatypes.generated.Uint32
import org.web3j.crypto.Credentials
import org.web3j.protocol.core.methods.request.EthFilter
import org.web3j.protocol.core.methods.response.BaseEventResponse
import org.web3j.tuples.generated.Tuple3
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
class V2PoolContract : Contract {
    @Deprecated("")
    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        gasPrice: BigInteger?,
        gasLimit: BigInteger?
    ) : super(
        V2PoolContract.Companion.BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        V2PoolContract.Companion.BINARY, contractAddress, web3j, credentials, contractGasProvider
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
        V2PoolContract.Companion.BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        transactionManager: TransactionManager?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        V2PoolContract.Companion.BINARY, contractAddress, web3j, transactionManager, contractGasProvider
    ) {
    }

    fun getApprovalEvents(transactionReceipt: TransactionReceipt?): List<ApprovalEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.APPROVAL_EVENT, transactionReceipt)
        val responses = ArrayList<ApprovalEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = ApprovalEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.owner = eventValues.indexedValues[0].value as String
            typedResponse.spender = eventValues.indexedValues[1].value as String
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun approvalEventFlowable(filter: EthFilter?): Flowable<ApprovalEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.APPROVAL_EVENT, log)
            val typedResponse = ApprovalEventResponse()
            typedResponse.log = log
            typedResponse.owner = eventValues.indexedValues[0].value as String
            typedResponse.spender = eventValues.indexedValues[1].value as String
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun approvalEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<ApprovalEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.APPROVAL_EVENT))
        return approvalEventFlowable(filter)
    }

    fun getBurnEvents(transactionReceipt: TransactionReceipt?): List<BurnEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.BURN_EVENT, transactionReceipt)
        val responses = ArrayList<BurnEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = BurnEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.amount0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1 = eventValues.nonIndexedValues[1].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun burnEventFlowable(filter: EthFilter?): Flowable<BurnEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.BURN_EVENT, log)
            val typedResponse = BurnEventResponse()
            typedResponse.log = log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.amount0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1 = eventValues.nonIndexedValues[1].value as BigInteger
            typedResponse
        }
    }

    fun burnEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<BurnEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.BURN_EVENT))
        return burnEventFlowable(filter)
    }

    fun getMintEvents(transactionReceipt: TransactionReceipt?): List<MintEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.MINT_EVENT, transactionReceipt)
        val responses = ArrayList<MintEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = MintEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.amount0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1 = eventValues.nonIndexedValues[1].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun mintEventFlowable(filter: EthFilter?): Flowable<MintEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.MINT_EVENT, log)
            val typedResponse = MintEventResponse()
            typedResponse.log = log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.amount0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1 = eventValues.nonIndexedValues[1].value as BigInteger
            typedResponse
        }
    }

    fun mintEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<MintEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.MINT_EVENT))
        return mintEventFlowable(filter)
    }

    fun getSwapEvents(transactionReceipt: TransactionReceipt?): List<SwapEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.SWAP_EVENT, transactionReceipt)
        val responses = ArrayList<SwapEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = SwapEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.amount0In = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1In = eventValues.nonIndexedValues[1].value as BigInteger
            typedResponse.amount0Out = eventValues.nonIndexedValues[2].value as BigInteger
            typedResponse.amount1Out = eventValues.nonIndexedValues[3].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun swapEventFlowable(filter: EthFilter?): Flowable<SwapEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.SWAP_EVENT, log)
            val typedResponse = SwapEventResponse()
            typedResponse.log = log
            typedResponse.sender = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.amount0In = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.amount1In = eventValues.nonIndexedValues[1].value as BigInteger
            typedResponse.amount0Out = eventValues.nonIndexedValues[2].value as BigInteger
            typedResponse.amount1Out = eventValues.nonIndexedValues[3].value as BigInteger
            typedResponse
        }
    }

    fun swapEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<SwapEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.SWAP_EVENT))
        return swapEventFlowable(filter)
    }

    fun getSyncEvents(transactionReceipt: TransactionReceipt?): List<SyncEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.SYNC_EVENT, transactionReceipt)
        val responses = ArrayList<SyncEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = SyncEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.reserve0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.reserve1 = eventValues.nonIndexedValues[1].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun syncEventFlowable(filter: EthFilter?): Flowable<SyncEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.SYNC_EVENT, log)
            val typedResponse = SyncEventResponse()
            typedResponse.log = log
            typedResponse.reserve0 = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.reserve1 = eventValues.nonIndexedValues[1].value as BigInteger
            typedResponse
        }
    }

    fun syncEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<SyncEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.SYNC_EVENT))
        return syncEventFlowable(filter)
    }

    fun getTransferEvents(transactionReceipt: TransactionReceipt?): List<V2PoolContract.TransferEventResponse> {
        val valueList = extractEventParametersWithLog(V2PoolContract.Companion.TRANSFER_EVENT, transactionReceipt)
        val responses: ArrayList<V2PoolContract.TransferEventResponse> =
            ArrayList<V2PoolContract.TransferEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse: V2PoolContract.TransferEventResponse = V2PoolContract.TransferEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun transferEventFlowable(filter: EthFilter?): Flowable<V2PoolContract.TransferEventResponse> {
        return web3j.ethLogFlowable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(V2PoolContract.Companion.TRANSFER_EVENT, log)
            val typedResponse: V2PoolContract.TransferEventResponse = V2PoolContract.TransferEventResponse()
            typedResponse.log = log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun transferEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<V2PoolContract.TransferEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(V2PoolContract.Companion.TRANSFER_EVENT))
        return transferEventFlowable(filter)
    }

    fun DOMAIN_SEPARATOR(): RemoteFunctionCall<ByteArray> {
        val function = Function(
            V2PoolContract.Companion.FUNC_DOMAIN_SEPARATOR,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Bytes32?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, ByteArray::class.java)
    }

    fun MINIMUM_LIQUIDITY(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_MINIMUM_LIQUIDITY,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun PERMIT_TYPEHASH(): RemoteFunctionCall<ByteArray> {
        val function = Function(
            V2PoolContract.Companion.FUNC_PERMIT_TYPEHASH,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Bytes32?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, ByteArray::class.java)
    }

    fun allowance(param0: String?, param1: String?): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_ALLOWANCE,
            Arrays.asList<Type<*>>(
                Address(160, param0),
                Address(160, param1)
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun approve(spender: String?, value: BigInteger?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_APPROVE,
            Arrays.asList<Type<*>>(
                Address(160, spender),
                Uint256(value)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun balanceOf(param0: String?): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_BALANCEOF,
            Arrays.asList<Type<*>>(Address(160, param0)),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun burn(to: String?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_BURN,
            Arrays.asList<Type<*>>(Address(160, to)), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun decimals(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_DECIMALS,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint8?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun factory(): RemoteFunctionCall<String> {
        val function = Function(
            V2PoolContract.Companion.FUNC_FACTORY,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    val reserves: RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>
        get() {
            val function = Function(
                V2PoolContract.Companion.FUNC_GETRESERVES,
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(
                    object : TypeReference<Uint112?>() {},
                    object : TypeReference<Uint112?>() {},
                    object : TypeReference<Uint32?>() {})
            )
            return RemoteFunctionCall(
                function
            ) {
                val results = executeCallMultipleValueReturn(function)
                Tuple3(
                    results[0].value as BigInteger,
                    results[1].value as BigInteger,
                    results[2].value as BigInteger
                )
            }
        }

    fun initialize(_token0: String?, _token1: String?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_INITIALIZE,
            Arrays.asList<Type<*>>(
                Address(160, _token0),
                Address(160, _token1)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun kLast(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_KLAST,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun mint(to: String?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_MINT,
            Arrays.asList<Type<*>>(Address(160, to)), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun name(): RemoteFunctionCall<String> {
        val function = Function(
            V2PoolContract.Companion.FUNC_NAME,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun nonces(param0: String?): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_NONCES,
            Arrays.asList<Type<*>>(Address(160, param0)),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun permit(
        owner: String?,
        spender: String?,
        value: BigInteger?,
        deadline: BigInteger?,
        v: BigInteger?,
        r: ByteArray?,
        s: ByteArray?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_PERMIT,
            Arrays.asList<Type<*>>(
                Address(160, owner),
                Address(160, spender),
                Uint256(value),
                Uint256(deadline),
                Uint8(v),
                Bytes32(r),
                Bytes32(s)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun price0CumulativeLast(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_PRICE0CUMULATIVELAST,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun price1CumulativeLast(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_PRICE1CUMULATIVELAST,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun skim(to: String?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_SKIM,
            Arrays.asList<Type<*>>(Address(160, to)), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun swap(
        amount0Out: BigInteger?,
        amount1Out: BigInteger?,
        to: String?,
        data: ByteArray?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_SWAP,
            Arrays.asList<Type<*>>(
                Uint256(amount0Out),
                Uint256(amount1Out),
                Address(160, to),
                DynamicBytes(data)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun symbol(): RemoteFunctionCall<String> {
        val function = Function(
            V2PoolContract.Companion.FUNC_SYMBOL,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun sync(): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_SYNC,
            Arrays.asList(), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun token0(): RemoteFunctionCall<String> {
        val function = Function(
            V2PoolContract.Companion.FUNC_TOKEN0,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun token1(): RemoteFunctionCall<String> {
        val function = Function(
            V2PoolContract.Companion.FUNC_TOKEN1,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun totalSupply(): RemoteFunctionCall<BigInteger> {
        val function = Function(
            V2PoolContract.Companion.FUNC_TOTALSUPPLY,
            Arrays.asList(),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun transfer(to: String?, value: BigInteger?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_TRANSFER,
            Arrays.asList<Type<*>>(
                Address(160, to),
                Uint256(value)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun transferFrom(from: String?, to: String?, value: BigInteger?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            V2PoolContract.Companion.FUNC_TRANSFERFROM,
            Arrays.asList<Type<*>>(
                Address(160, from),
                Address(160, to),
                Uint256(value)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    class ApprovalEventResponse : BaseEventResponse() {
        var owner: String? = null
        var spender: String? = null
        var value: BigInteger? = null
    }

    class BurnEventResponse : BaseEventResponse() {
        var sender: String? = null
        var to: String? = null
        var amount0: BigInteger? = null
        var amount1: BigInteger? = null
    }

    class MintEventResponse : BaseEventResponse() {
        var sender: String? = null
        var amount0: BigInteger? = null
        var amount1: BigInteger? = null
    }

    class SwapEventResponse : BaseEventResponse() {
        var sender: String? = null
        var to: String? = null
        var amount0In: BigInteger? = null
        var amount1In: BigInteger? = null
        var amount0Out: BigInteger? = null
        var amount1Out: BigInteger? = null
    }

    class SyncEventResponse : BaseEventResponse() {
        var reserve0: BigInteger? = null
        var reserve1: BigInteger? = null
    }

    class TransferEventResponse : BaseEventResponse() {
        var from: String? = null
        var to: String? = null
        var value: BigInteger? = null
    }

    companion object {
        const val BINARY = "Bin file was not provided"
        const val FUNC_DOMAIN_SEPARATOR = "DOMAIN_SEPARATOR"
        const val FUNC_MINIMUM_LIQUIDITY = "MINIMUM_LIQUIDITY"
        const val FUNC_PERMIT_TYPEHASH = "PERMIT_TYPEHASH"
        const val FUNC_ALLOWANCE = "allowance"
        const val FUNC_APPROVE = "approve"
        const val FUNC_BALANCEOF = "balanceOf"
        const val FUNC_BURN = "burn"
        const val FUNC_DECIMALS = "decimals"
        const val FUNC_FACTORY = "factory"
        const val FUNC_GETRESERVES = "getReserves"
        const val FUNC_INITIALIZE = "initialize"
        const val FUNC_KLAST = "kLast"
        const val FUNC_MINT = "mint"
        const val FUNC_NAME = "name"
        const val FUNC_NONCES = "nonces"
        const val FUNC_PERMIT = "permit"
        const val FUNC_PRICE0CUMULATIVELAST = "price0CumulativeLast"
        const val FUNC_PRICE1CUMULATIVELAST = "price1CumulativeLast"
        const val FUNC_SKIM = "skim"
        const val FUNC_SWAP = "swap"
        const val FUNC_SYMBOL = "symbol"
        const val FUNC_SYNC = "sync"
        const val FUNC_TOKEN0 = "token0"
        const val FUNC_TOKEN1 = "token1"
        const val FUNC_TOTALSUPPLY = "totalSupply"
        const val FUNC_TRANSFER = "transfer"
        const val FUNC_TRANSFERFROM = "transferFrom"
        val APPROVAL_EVENT = Event(
            "Approval",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {})
        )
        val BURN_EVENT = Event(
            "Burn",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Address?>(true) {})
        )
        val MINT_EVENT = Event(
            "Mint",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Uint256?>() {})
        )
        val SWAP_EVENT = Event(
            "Swap",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Address?>(true) {})
        )
        val SYNC_EVENT = Event(
            "Sync",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Uint112?>() {},
                object : TypeReference<Uint112?>() {})
        )
        val TRANSFER_EVENT = Event(
            "Transfer",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {})
        )

        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): V2PoolContract {
            return V2PoolContract(contractAddress, web3j, credentials, gasPrice, gasLimit)
        }

        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): V2PoolContract {
            return V2PoolContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            contractGasProvider: ContractGasProvider?
        ): V2PoolContract {
            return V2PoolContract(contractAddress, web3j, credentials, contractGasProvider)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            contractGasProvider: ContractGasProvider?
        ): V2PoolContract {
            return V2PoolContract(contractAddress, web3j, transactionManager, contractGasProvider)
        }
    }
}