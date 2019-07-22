package angel.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import angel.service.jsa.EntryPostContract;

@SpringBootApplication
public class Application{

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args){
    // TODO Auto-generated method stub

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

      log.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
    } catch(IOException e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return web3j;
  }

  @Bean
  public Credentials initializeCredential(){
    Credentials credentials = Credentials.create("f79ca3575548cac9e94cdf3b811570f9fc67b45e074fab1cc00f51fcde902e5c");
    log.info("Credentials loaded");

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
      log.info("All Contracts loaded");
    } catch(Exception e){
      // TODO Auto-generated catch block
      log.info("initailizeContracts failed");
      e.printStackTrace();
    }

    return entryPostContract;
  }

}
