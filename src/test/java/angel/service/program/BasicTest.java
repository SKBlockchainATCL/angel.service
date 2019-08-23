package angel.service.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Bytes2;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes8;

public class BasicTest{
  
  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  
  
  @Test
  public void testFunction1() {
    
    final Function func = new Function("function1",
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint>() {}));
    
    java.util.List<Type> inParams = func.getInputParameters();
    java.util.List<TypeReference<Type>> outParams = func.getOutputParameters();
    
    Assertions.assertEquals(inParams.size(), 0, "No input parameter is specifed but found non-zero input parameters.");
    Assertions.assertEquals(outParams.size(), 1, "Only 1 output parameter is specified, but...");
    
  }
  
  @Test
  public void testFunction2() {
    
    List<Type> inParams = new ArrayList<>();
    List<TypeReference<?>> outParams = new ArrayList<>();
 
    //inParams.add(new Bytes32("The Program 1".getBytes()));
    inParams.add(new Bytes8("20190701".getBytes()));
    inParams.add(new Bytes8("201907".getBytes()));
    
    outParams.add(TypeReference.create(Bytes1.class));
    outParams.add(TypeReference.create(Bytes2.class));
 
    final Function func = new Function("function2",
        inParams, outParams);
    
    java.util.List<Type> inParams1 = func.getInputParameters();
    java.util.List<TypeReference<Type>> params2 = func.getOutputParameters();
    
    Assertions.assertEquals(inParams1.size(), 3, "Found wrong number of input parameters.");
    Assertions.assertEquals(outParams.size(), 2, "Found wrong number of output parameters.");
    
  }

}
