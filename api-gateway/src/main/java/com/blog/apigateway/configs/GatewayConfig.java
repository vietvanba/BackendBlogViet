package com.blog.apigateway.configs;

import com.blog.apigateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("mail-service", r -> r.path("/api/mail/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .addResponseHeader("Access-Control-Allow-Origin", "*")
                                .addResponseHeader("Access-Control-Allow-Methods", "*")
                                .addResponseHeader("Access-Control-Allow-Headers", "*")
                        )
                        .uri("http://localhost:8081"))
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .addResponseHeader("Access-Control-Allow-Origin", "*")
                                .addResponseHeader("Access-Control-Allow-Methods", "*")
                                .addResponseHeader("Access-Control-Allow-Headers", "*")
                        )
                        .uri("http://localhost:8082"))
                .route("location-service", r -> r.path("/api/location/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .addResponseHeader("Access-Control-Allow-Origin", "*")
                                .addResponseHeader("Access-Control-Allow-Methods", "*")
                                .addResponseHeader("Access-Control-Allow-Headers", "*")
                        )
                        .uri("http://localhost:8083"))
                .build();
    }

}
