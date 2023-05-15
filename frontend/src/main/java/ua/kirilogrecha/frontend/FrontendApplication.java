package ua.kirilogrecha.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}

	@Bean
	public RouteLocator backendLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/backend/**")
						.filters(fs -> fs.rewritePath(
								"/backend/(?<handle>.*)",
								"/${handle}"))
						.uri("http://localhost:8081/"))
				.build();
	}

}
