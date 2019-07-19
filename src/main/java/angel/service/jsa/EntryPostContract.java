package angel.service.jsa;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple8;
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
    private static final String BINARY = "608060405234801561001057600080fd5b5061027c806100206000396000f3fe6080604052600436106100295760003560e01c8063a3ba03611461002e578063c889c2f51461007f575b600080fd5b61007d600480360361012081101561004557600080fd5b5080359060208101359060408101359060608101359060808101359060a08101359060c08101359060e08101359061010001356100ea565b005b34801561008b57600080fd5b506100a9600480360360208110156100a257600080fd5b5035610169565b604080519889526020890197909752878701959095526060870193909352608086019190915260a085015260c084015260e083015251908190036101000190f35b6100f2610203565b97885260208089019788526040808a0197885260608a0196875260808a0195865260a08a0194855260c08a0193845260e08a0192835260009a8b52908a9052909820965187559451600187015592516002860155905160038501555160048401555160058301555160068201559051600790910155565b60008060008060008060008061017d610203565b50505060009687525050506020848152604094859020855161010081018752815480825260018301549382018490526002830154978201889052600383015460608301819052600484015460808401819052600585015460a08501819052600686015460c0860181905260079096015460e0909501859052929a95999850909650945092565b6040805161010081018252600080825260208201819052918101829052606081018290526080810182905260a0810182905260c0810182905260e08101919091529056fea265627a7a723058203829311efa8b347428d98433c5ee1c56f56e0ea6835303e6e8dc28a97834b27164736f6c63430005090032";

    public static final String FUNC_SETENTRYPOST = "setEntryPost";

    public static final String FUNC_GETENTRYPOST = "getEntryPost";

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

    public RemoteCall<TransactionReceipt> setEntryPost(byte[] id, byte[] contentsId, byte[] photoId1, byte[] photoId2, byte[] photoId3, byte[] postedAt, BigInteger likes, byte[] serviceProgramId, byte[] userId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.web3j.abi.datatypes.generated.Bytes32(contentsId), 
                new org.web3j.abi.datatypes.generated.Bytes32(photoId1), 
                new org.web3j.abi.datatypes.generated.Bytes32(photoId2), 
                new org.web3j.abi.datatypes.generated.Bytes32(photoId3), 
                new org.web3j.abi.datatypes.generated.Bytes32(postedAt), 
                new org.web3j.abi.datatypes.generated.Uint256(likes), 
                new org.web3j.abi.datatypes.generated.Bytes32(serviceProgramId), 
                new org.web3j.abi.datatypes.generated.Bytes32(userId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>> getEntryPost(byte[] hashId) {
        final Function function = new Function(FUNC_GETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(hashId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>>(
                new Callable<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>>() {
                    @Override
                    public Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (byte[]) results.get(6).getValue(), 
                                (byte[]) results.get(7).getValue());
                    }
                });
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
