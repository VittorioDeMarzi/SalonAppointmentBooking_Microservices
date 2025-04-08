package com.demadev.gateway_server.configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Booking Service
                .route("booking-service", r -> r
                        .path("/api/v1/bookings/**")
                        .uri("lb://BOOKING-SERVICE")
                )
                // Payment Service
                .route("payment-service", r -> r
                        .path("/api/v1/payments/**")
                        .uri("lb://PAYMENT-SERVICE")
                )
                .route("category-service", r -> r
                        .path("/api/v1/categories/**", "/api/v1/categories/salon-owner/**")
                        .uri("lb://CATEGORY-SERVICE")
                )
                .route("salon-service", r -> r
                        .path("/api/v1/salons/**", "/api/v1/admin/salons")
                        .uri("lb://SALON-SERVICE")
                )
                .route("service-offering", r -> r
                        .path("/api/v1/service-offering/**")
                        .uri("lb://SERVICE-OFFERING")
                )
                .route("user-service", r -> r
                        .path( "/api/v1/user/**")
                        .uri("lb://USER-SERVICE")
                )
                .build();
    }
}
