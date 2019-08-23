package angel.service.program;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import angel.support.web3j.AbstractEthereumContract;

@Repository
@ManagedResource(
    objectName="angel:type=bean,name=programFactoryContract",
    description="Adapter for smart contract 'ServiceProgramFactory"
)
@ParametersAreNonnullByDefault
public class ProgramFactoryContract extends AbstractEthereumContract{
  
  
  @Autowired
  public ProgramFactoryContract(Web3j ethereum, @Value("${contracts.ServiceProgramFactory.address}") String addr) {
    super(ethereum, addr);
  }
  
  
  public BigInteger countServicePrograms(@NotBlank final String from) {
    logger.debug("Calling ServiceProgramFactory.countServicePrograms() at {}", this.getAddress());
 
    Arrays.asList();
    final Function func = new Function("countServicePrograms",
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint>() {}));
    final String encodedFunc = FunctionEncoder.encode(func);
    
    final EthCall resp;
    try {
      resp = this.getEthereum().ethCall( 
          Transaction.createEthCallTransaction(from, this.getAddress(), encodedFunc),
          DefaultBlockParameterName.LATEST).sendAsync().get();
     
      if(resp.hasError()) throw new RuntimeException(
          String.format("%s (code: %d, data: %s)", resp.getError().getMessage(), resp.getError().getCode(), resp.getError().getData()));
      
      List<Type> output = FunctionReturnDecoder.decode(resp.getValue(), func.getOutputParameters());
      return ((Uint)(output.get(0))).getValue();
    }catch(Exception ex) {
      this.logger.error(ex.getMessage(), ex);
      
      if(ex instanceof RuntimeException) throw (RuntimeException)ex;
      else throw new RuntimeException(ex);
    }

  }

}
