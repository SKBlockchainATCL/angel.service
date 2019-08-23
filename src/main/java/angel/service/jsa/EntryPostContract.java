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
    private static final String BINARY = "608060405234801561001057600080fd5b5061030d806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80634a5b987214610046578063d448bae314610090578063db90581d146100bf575b600080fd5b61008e600480360361010081101561005d57600080fd5b5080359060208101359060408101359060608101359060808101359060a08101359060c08101359060e00135610123565b005b6100ad600480360360208110156100a657600080fd5b50356101b9565b60408051918252519081900360200190f35b6100e2600480360360408110156100d557600080fd5b50803590602001356101cb565b604080519889526020890197909752878701959095526060870193909352608086019190915260a085015260c084015260e083015251908190036101000190f35b61012b610294565b97885260208089019788526040808a0197885260608a0196875260808a0195865260a08a0194855260c08a0193845260e08a018381526000938452838352908320805460018181018355918552929093209951600890920290990190815596519087015593516002860155915160038501555160048401555160058301555160068201559051600790910155565b60009081526020819052604090205490565b6000806000806000806000806101df610294565b60008b815260208190526040902080548b9081106101f957fe5b600091825260209182902060408051610100810182526008909302909101805480845260018201549484018590526002820154928401839052600382015460608501819052600483015460808601819052600584015460a08701819052600685015460c0880181905260079095015460e0909701879052929f50959d50929b5091995092975095509093509150509295985092959890939650565b6040805161010081018252600080825260208201819052918101829052606081018290526080810182905260a0810182905260c0810182905260e08101919091529056fea265627a7a72305820c1bb9cdc06bde500565c5dc5a083b52c0848e7edad5d4306569457d12aa5b1fa64736f6c63430005090032";

    public static final String FUNC_SETENTRYPOST = "setEntryPost";

    public static final String FUNC_GETENTRYPOSTLENGTHBYUSER = "getEntryPostLengthByUser";

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

    public RemoteCall<TransactionReceipt> setEntryPost(byte[] _contentsId, byte[] _photoId1, byte[] _photoId2, byte[] _photoId3, byte[] _postedAt, BigInteger _likes, byte[] _serviceProgramId, byte[] _userId) {
        final Function function = new Function(
                FUNC_SETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_contentsId), 
                new org.web3j.abi.datatypes.generated.Bytes32(_photoId1), 
                new org.web3j.abi.datatypes.generated.Bytes32(_photoId2), 
                new org.web3j.abi.datatypes.generated.Bytes32(_photoId3), 
                new org.web3j.abi.datatypes.generated.Bytes32(_postedAt), 
                new org.web3j.abi.datatypes.generated.Uint256(_likes), 
                new org.web3j.abi.datatypes.generated.Bytes32(_serviceProgramId), 
                new org.web3j.abi.datatypes.generated.Bytes32(_userId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getEntryPostLengthByUser(byte[] _userId) {
        final Function function = new Function(FUNC_GETENTRYPOSTLENGTHBYUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_userId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>> getEntryPost(byte[] _user, BigInteger _idx) {
        final Function function = new Function(FUNC_GETENTRYPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_user), 
                new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
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
