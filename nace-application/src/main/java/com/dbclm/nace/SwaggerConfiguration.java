package com.dbclm.nace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerConfiguration {
	
	  @Bean
	  public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	  .select()
	  .apis(RequestHandlerSelectors.basePackage("com.dbclm.nace.controller"))
	  .paths(PathSelectors.any()) .build(); 
	  }
	  
	  @Bean UiConfiguration uiConfig() { return
	  UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build(); }
	 
    
}
