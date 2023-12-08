package com.ascend.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    
    @Value("${gateway.passroute}")
    private String passroute; 
    
	@Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
		if(passroute != null) {
		String[] passroutes = passroute.split(",");
        return builder.routes()
                .route("ECartPGPTen", r -> r.path(passroutes[0])
                        .filters(f -> f.filter(filter))
                        .uri("lb://ECartPGPTen"))

                .route("USERDASHBOARD", r -> r.path(passroutes[1])
                        .filters(f -> f.filter(filter))
                        .uri("lb://USERDASHBOARD"))
                .build();
		}
		return builder.routes().build();
    }

}
