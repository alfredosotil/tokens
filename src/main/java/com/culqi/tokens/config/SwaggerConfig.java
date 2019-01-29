package com.culqi.tokens.config;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.culqi.tokens"))
				.paths(PathSelectors.regex("/api.*"))
				.apis(RequestHandlerSelectors.any())  // If you want to list all the apis including springboots own
				.build()
				.apiInfo(metaInfo())
//                .securitySchemes(Arrays.asList(apiKey()))
				.pathMapping("/")
				.useDefaultResponseMessages(false)
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class);
	}

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header"); //`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
    }

    /**
	 * Return the meta info about the API. This should reflect
	 */
	private ApiInfo metaInfo() {
		return new ApiInfoBuilder()
				.description("Backend API to generate tokens")
				.title("Culqi API")
				.version("Unreleased [WIP]")
				.build();
	}

}
