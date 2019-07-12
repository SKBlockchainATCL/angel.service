package angel.service;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import angel.service.program.ServiceProgramController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
    ServiceProgramController.class
})
public class Application{

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args){
    // TODO Auto-generated method stub
    SpringApplication.run(Application.class, args);
  }
  
  
  @Bean
  public Docket restApi() {
    
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .directModelSubstitute(LocalDate.class, String.class);
  }

}
