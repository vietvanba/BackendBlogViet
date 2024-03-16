package com.blog.apigateway.configs;

import com.blog.apigateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("mail-service", r -> r.path("/api/mail/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8081"))
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8082"))
                .route("location-service", r -> r.path("/api/location/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8083"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> corsPreflight() {
        return RouterFunctions.route()
                .OPTIONS("/api/**", this::handleOptions)
                .build();
    }

    private Mono<ServerResponse> handleOptions(ServerRequest request) {
        return ServerResponse.ok()
                .headers(headers -> {
                    headers.addAll("Access-Control-Allow-Origin", Collections.singletonList("https://vanbaviet.com"));
                    headers.addAll("Access-Control-Allow-Methods", Collections.singletonList("GET,POST,PUT,DELETE,OPTIONS"));
                    headers.addAll("Access-Control-Allow-Headers", Collections.singletonList("Authorization,Content-Type"));
                })
                .build();
    }

}
