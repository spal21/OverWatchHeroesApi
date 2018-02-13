
package com.dojo.overwatch.heroes.component.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@ComponentScan({ "com.dojo.overwatch.heroes.component","com.dojo.overwatch.heroes.web" })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("OverwatchHeroesRestApi").select()
				.apis(RequestHandlerSelectors.basePackage("com.dojo.overwatch.heroes.web")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Overwatch Heroes Rest with Swagger").description("Overwatch Heroes Rest with Swagger")
				.contact("Sourav Pal").version("1.0").build();
	}
	
	@Bean
	public  PropertyPlaceholderConfigurer propertyPlaceholderConfig(){
		PropertyPlaceholderConfigurer config = new PropertyPlaceholderConfigurer();
		config.setLocation(new ClassPathResource("main/resources/app.properties") );
		config.setIgnoreUnresolvablePlaceholders(false);
		return config;
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourceConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
