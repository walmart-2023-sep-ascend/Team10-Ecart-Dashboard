package com.ascend.apigateway.config;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {

	@Value("${auth.routes}")
    private String secret;
	
	
    public static List<String> openApiEndpoints = List.of(
            "/ECartPGPTen/api/ecarto/status,/auth/toke"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
            
    public boolean validate(ServerHttpRequest request) {
    	openApiEndpoints = List.of(secret.split(","));
    	return 
    	openApiEndpoints
        .stream()
        .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

}
