package cl.adepti.merkenacuatico;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import cl.adepti.merkenacuatico.data.DocumentStorageService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);

		app.run(args);
	}
	
	@Bean
    MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }
	@Bean
	DocumentStorageService documentStorageService() {
		return new DocumentStorageService();
	}
	
}
