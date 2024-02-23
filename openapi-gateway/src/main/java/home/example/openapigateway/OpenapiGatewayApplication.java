package home.example.openapigateway;

import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@SpringBootApplication
public class OpenapiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenapiGatewayApplication.class, args);
	}

}

@Configuration
class SwaggerConfig {

	@Bean
	public CommandLineRunner openApiGroups(
			RouteDefinitionLocator locator,
			SwaggerUiConfigParameters swaggerUiParameters) {
		return args -> Objects.requireNonNull(locator
						.getRouteDefinitions().collectList().block())
				.stream()
				.map(RouteDefinition::getId)
				.filter(id -> id.matches(".*-service"))
				.map(id -> id.replace("-service", ""))
				.forEach(swaggerUiParameters::addGroup);
	}
}
