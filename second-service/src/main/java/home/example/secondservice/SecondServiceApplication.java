package home.example.secondservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class SecondServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondServiceApplication.class, args);
	}

}

@RestController
@RequestMapping("api")
@CrossOrigin
class SecondController{
	@GetMapping("/{value}")
	String get(@PathVariable("value") String value){
		return "Second service return " + value;
	}
}

@Configuration
class SpringFoxConfig {
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("second")
				.packagesToScan("home.example.secondservice")
				.build();
	}
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components());
	}
}
