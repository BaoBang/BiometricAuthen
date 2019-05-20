package com.baobang.biometric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  * @author BaoBang
  * @Created May 15, 2019
  * 
  */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket userAPI() {
		
//		//Adding Header
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("headerName").modelRef(new ModelRef("string"))
//        .parameterType("header").required(true).build();
//        List<Parameter> aParameters = new ArrayList<Parameter>();
//        aParameters.add(aParameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
//			.host("invoice-piobill.eprocon.us")
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.baobang.biometric.controller"))
			.paths(PathSelectors.regex(".*"))
			.build()
			.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("Biometric Authenticatioin with CRAM API")
	        .description("Using Spring Boot and Springfox for Swagger")
	        .contact(new Contact("Nguyễn Bảo Bằng", "", "baobangb5@gmail.com"))
	        .version("1.0.0.1")
	        .build();
	}
}
