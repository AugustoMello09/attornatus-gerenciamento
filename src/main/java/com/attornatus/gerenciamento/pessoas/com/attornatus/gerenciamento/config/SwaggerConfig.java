package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build()
                .apiInfo(metaInfo());
    }
	
	private ApiInfo metaInfo() {

	        @SuppressWarnings("rawtypes")
			ApiInfo apiInfo = new ApiInfo(
	                "Attornatus API REST",
	                "API REST de cadastro de endereços e pessoas.",
	                "1.0",
	                "Terms of Service",
	                new Contact("José Augusto", "https://www.linkedin.com/in/jos%C3%A9-augusto-mello-794a94234",
	                        "joseaugusto.Mello01@gmail.com"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
	        );

	        return apiInfo;
	    }

   

}
