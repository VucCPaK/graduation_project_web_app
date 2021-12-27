package Spring.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // docker 0.0.0.0 - 192.168.42.111
    // localhost localhost

    // named as service

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/api/backend/**")
//                        .uri("http://backend:8081/"))
//                .route(r -> r.path("/api/mongo/**")
//                        .uri("http://pictures:8087/"))
//                .route(r -> r.path("/auth/**")
//                        .uri("http://keycloak:8080/"))
//                .build();
//    }
}
