package home.example.firstservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class FirstServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}

}

@RestController
@RequestMapping("/api")
@CrossOrigin
class FirstController{
	@GetMapping("/{value}")
	String get(@PathVariable("value") String value){
		return "First service return " + value;
	}
	@PostMapping("/demo")
	String post(@RequestBody String text){
		return "Demo text: "+text;
	}
}

@Configuration
class SpringFoxConfig {
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("first")
				.packagesToScan("home.example.firstservice")
				.build();
	}
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components());
	}
}
