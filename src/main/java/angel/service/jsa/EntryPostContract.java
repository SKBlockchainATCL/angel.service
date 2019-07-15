package angel.service.jsa;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class EntryPostContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061094e806100206000396000f3fe6080604052600436106100295760003560e01c80635cf4abfa1461002e578063deb878d014610154575b600080fd5b34801561003a57600080fd5b506100df6004803603602081101561005157600080fd5b810190602081018135600160201b81111561006b57600080fd5b82018360208201111561007d57600080fd5b803590602001918460018302840111600160201b8311171561009e57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506105a6945050505050565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610119578181015183820152602001610101565b50505050905090810190601f1680156101465780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6105a4600480360361012081101561016b57600080fd5b810190602081018135600160201b81111561018557600080fd5b82018360208201111561019757600080fd5b803590602001918460018302840111600160201b831117156101b857600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561020a57600080fd5b82018360208201111561021c57600080fd5b803590602001918460018302840111600160201b8311171561023d57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561028f57600080fd5b8201836020820111156102a157600080fd5b803590602001918460018302840111600160201b831117156102c257600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561031457600080fd5b82018360208201111561032657600080fd5b803590602001918460018302840111600160201b8311171561034757600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561039957600080fd5b8201836020820111156103ab57600080fd5b803590602001918460018302840111600160201b831117156103cc57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561041e57600080fd5b82018360208201111561043057600080fd5b803590602001918460018302840111600160201b8311171561045157600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092958435959094909350604081019250602001359050600160201b8111156104ab57600080fd5b8201836020820111156104bd57600080fd5b803590602001918460018302840111600160201b831117156104de57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561053057600080fd5b82018360208201111561054257600080fd5b803590602001918460018302840111600160201b8311171561056357600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061069e945050505050565b005b60606000826040518082805190602001908083835b602083106105da5780518252601f1990920191602091820191016105bb565b518151600019602094850361010090810a8201928316921993909316919091179092529490920196875260408051978890038201882060019081018054601f600293821615909802909501909416049485018290048202880182019052838752909450919250508301828280156106925780601f1061066757610100808354040283529160200191610692565b820191906000526020600020905b81548152906001019060200180831161067557829003601f168201915b50505050509050919050565b6106a6610832565b60208082018a905260408083018a9052606083018990526080830188905260a0830187905260c0830186905260e083018590526101008301849052518b5183926000928e9290918291908401908083835b602083106107165780518252601f1990920191602091820191016106f7565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208451805191946107579450859350019061087e565b506020828101518051610770926001850192019061087e565b506040820151805161078c91600284019160209091019061087e565b50606082015180516107a891600384019160209091019061087e565b50608082015180516107c491600484019160209091019061087e565b5060a082015180516107e091600584019160209091019061087e565b5060c0820151600682015560e0820151805161080691600784019160209091019061087e565b50610100820151805161082391600884019160209091019061087e565b50505050505050505050505050565b6040518061012001604052806060815260200160608152602001606081526020016060815260200160608152602001606081526020016000815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106108bf57805160ff19168380011785556108ec565b828001600101855582156108ec579182015b828111156108ec5782518255916020019190600101906108d1565b506108f89291506108fc565b5090565b61091691905b808211156108f85760008155600101610902565b9056fea265627a7a723058208fa8193aadba1483d887832801862923f57435ba1c5cb546af5b7af1856676a564736f6c63430005090032";

    public static final String FUNC_GETENTRYPOST = "getEntryPost";

    public static final String FUNC_SETENTRYPOST = "setEntryPost";

    @Deprecated
    protected EntryPostContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EntryPostContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EntryPostContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EntryPostContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getEntryPost(String id) {
        final Function function = new Function(FUNC_GETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setEntryPost(String id, String contentsId, String photoId1, String photoId2, String photoId3, String postedAt, BigInteger likes, String serviceProgramId, String userId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(id), 
                new org.web3j.abi.datatypes.Utf8String(contentsId), 
                new org.web3j.abi.datatypes.Utf8String(photoId1), 
                new org.web3j.abi.datatypes.Utf8String(photoId2), 
                new org.web3j.abi.datatypes.Utf8String(photoId3), 
                new org.web3j.abi.datatypes.Utf8String(postedAt), 
                new org.web3j.abi.datatypes.generated.Int256(likes), 
                new org.web3j.abi.datatypes.Utf8String(serviceProgramId), 
                new org.web3j.abi.datatypes.Utf8String(userId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static EntryPostContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EntryPostContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EntryPostContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EntryPostContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EntryPostContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EntryPostContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EntryPostContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EntryPostContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EntryPostContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EntryPostContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EntryPostContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EntryPostContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EntryPostContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EntryPostContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EntryPostContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EntryPostContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
