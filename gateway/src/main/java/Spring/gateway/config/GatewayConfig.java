package Spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/backend/**")
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/api/mongo/**")
                        .uri("http://localhost:8087/"))
                .route(r -> r.path("/auth/**")
                        .uri("http://localhost:8085/"))
                .build();
    }
}
