package angel.service.program;

import java.util.Arrays;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.Web3j;
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
  
  
  public int countServicePrograms() {
    logger.debug("Calling ServiceProgramFactory.countServicePrograms() at {}", this.getAddress());
 
    Arrays.asList();
    final Function func = new Function("countServicePrograms",
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint>() {}));
    final String encodedFunc = FunctionEncoder.encode(func);
    
    final EthCall resp;
    
    
    
    return 0;
  }

}
