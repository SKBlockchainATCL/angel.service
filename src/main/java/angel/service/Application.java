package angel.service;

import java.io.IOException;
import java.time.LocalDate;
import javax.annotation.Nonnull;
import javax.annotation.WillNotClose;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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
import angel.service.program.ProgramController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
// @EnableSwagger2
@ComponentScan(basePackageClasses = {ProgramController.class})
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

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .directModelSubstitute(LocalDate.class, String.class);
  }
  
  
  @Bean
  public Web3j ethereum(@Value("${ethereum.host}") @NotEmpty String host,
        @Value("${ethereum.port}") @Positive int port,
        @Value("${ethereum.netVersion}") @NotEmpty String netVersion) {
     return this.buildWeb3j("http", host, port, netVersion);
  }


  private Web3j buildWeb3j(@NotEmpty String protocol, @NotEmpty String host, int port, @NotEmpty String netVersion) {
     Web3j instance = Web3j.build(new HttpService(String.format("%s://%s:%d", protocol, host, port)));

     logger.info("Ethereum connector is created for {}://{}:{}", protocol, host, port);

     try {
       validateEthereumNetVersion(instance, netVersion);
       logger.info("Successfully validate the network ID of the specified Ethereum at {}://{}:{}", protocol, host, port);
     } catch(Exception ex) {
       logger.warn(String.format("Fail to validate network ID of the specified Ethereum at %s://%s:%d", protocol, host, port), ex);
     }

     return instance;
  }

  private void validateEthereumNetVersion(@Nonnull @WillNotClose Web3j ethereum, @Nonnull String ver) throws Exception{
     if (ethereum == null) throw new RuntimeException("...");

     String ver1 = ethereum.netVersion().send().getNetVersion();

     Validate.isTrue(StringUtils.equals(ver1, ver));
  }

}
