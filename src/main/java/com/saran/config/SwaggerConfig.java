package com.saran.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;
@Configuration
@EnableSwagger2
public class SwaggerConfig{
	 
	
	@Bean
	public Docket buildMyAPI() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(saranInfo()).select().paths(saranPaths()).build();
		
	}
	
	private Predicate<String> saranPaths() {
		
		return or(regex("/api.*"));
	}

	public void addResourceHandler(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");
		
	}
	
	private ApiInfo saranInfo() {
		return new ApiInfoBuilder().title("Sarans API")
				.description("Sarans API Reference for Developers")
				.termsOfServiceUrl("http://saran.com")
				.contact("saran@gmail.com")
				.licenseUrl("saran@gmail.com")
				.version("1.1")
				.build();
		
	}

}
