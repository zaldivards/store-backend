package sv.edu.ues.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		Contact contact = new Contact(
				"Christian Zaldivar",
				"https://github.com/Zaldivar97",
				"herrerachristian1897@gmail.com");
		
		return new ApiInfo(
				"Spring rest server",
				"spring framework begginer to guru",
				"1.0.0", 
				"something",
				contact,
				"Apache license",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
	
}











