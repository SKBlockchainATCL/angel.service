package angel.service.program;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class ProgramFactoryContractTest{

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  Environment env;
  
  @Autowired
  private ProgramFactoryContract testee;
  
  
  @Test
  public void testCountServicePrograms() {
    
    String from = env.getProperty("ethereum.from");
    
    BigInteger cnt = testee.countServicePrograms(from);
    
    logger.info("Number of found programs: {}", cnt.toString());
    
  }
  
  
}
