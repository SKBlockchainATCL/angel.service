package angel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import angel.service.jsa.EntryPostContract;


@SpringBootApplication
// @EnableSwagger2
public class Application{

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  public static void main(String[] args){
    // TODO Auto-generated method stub
    SpringApplication.run(Application.class, args);
  }
  
//  @PostConstruct
//  public void initailizeTestData() {
//    Web3j web3j = initailizeWeb3();
//    //entryPostService.
//  }
//
//  @Bean
//  public Docket restApi(){
//
//    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
//        .directModelSubstitute(LocalDate.class, String.class);
//  }

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
  private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

//  @Bean(name = "multipartResolver")
//  public CommonsMultipartResolver multipartResolver() {
//      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//      multipartResolver.setMaxUploadSize(100000);
//      return multipartResolver;
//  }
//
//  @Bean
//  public MultipartResolver multipartResolver() {
//  org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//  multipartResolver.setMaxUploadSize(512000000);
//  return multipartResolver;
//  }
//  @Bean
//  public MultipartResolver multipartResolver() {
//
//      CommonsMultipartResolver cmr = new CommonsMultipartResolver();
//      cmr.setMaxUploadSize(maxUploadSizeInMb * 2);
//      cmr.setMaxUploadSizePerFile(maxUploadSizeInMb); //bytes
//      return cmr;
//
//  }

}
