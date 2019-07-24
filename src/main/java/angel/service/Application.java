package angel.service;

import java.io.IOException;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import angel.service.jsa.EntryPostContract;
import angel.service.program.ServiceProgramController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
// @EnableSwagger2
@ComponentScan(basePackageClasses = {ServiceProgramController.class})
@Import({BeanValidatorPluginsConfiguration.class, 
  RedisConfig.class})
public class Application{

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args){
    // TODO Auto-generated method stub
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Docket restApi(){

    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
        .directModelSubstitute(LocalDate.class, String.class);
  }

  @Value("eth.myserver.url")
  private String ethClientUrl;

  @Bean
  public Web3j initailizeWeb3(){

    Web3j web3j = null;

    try{
      // We start by creating a new web3j instance to connect to remote nodes on
      // the network.
      // Note: if using web3j Android, use Web3jFactory.build(...
      // other network
      // example - Web3j web3j = Web3j.build(new
      // HttpService("https://rinkeby.infura.io/<your token>")); // FIXME:
      web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));

      logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
    } catch(Throwable ex){
      logger.error("Fail to connect to Ethereum node", ex);
    }
    return web3j;
  }

  @Bean
  public Credentials initializeCredential(){
    Credentials credentials = Credentials.create("f79ca3575548cac9e94cdf3b811570f9fc67b45e074fab1cc00f51fcde902e5c");
    logger.info("Credentials loaded");

    return credentials;
  }

  // ContractGasProvider contractGasProvider = new DefaultGasProvider();
  @Bean
  public ContractGasProvider initializeGasProvider(){

    return new DefaultGasProvider();
  }

  @Bean
  public EntryPostContract initailizeContracts(){

    EntryPostContract entryPostContract = null;

    try{
      entryPostContract = EntryPostContract.deploy(initailizeWeb3(), initializeCredential(), initializeGasProvider()).send();
      logger.info("Successfully initialized contracts.");
    } catch(Exception ex){
      logger.error("Fail to initialize contracts.", ex);
    }

    return entryPostContract;
  }

}
