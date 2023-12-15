package com.ascend.apigateway.config;

import static  org.springframework.http.HttpStatus.BAD_REQUEST;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.ascend.apigateway.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

/**
 * Raju M
 */
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

	private final RouterValidator routerValidator;
	private final JwtUtil jwtUtil;

	
	@Autowired
	public AuthenticationFilter(RouterValidator routerValidator, JwtUtil jwtUtil) {
		this.routerValidator = routerValidator;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		if (routerValidator.validate(request)) {
			if (this.isAuthMissing(request)) {
				return this.onError(exchange, HttpStatus.UNAUTHORIZED, null);
			}

			final String token = this.getAuthHeader(request);

			try {
				boolean flag = jwtUtil.isInvalid(token);
				if (flag) {
					return this.onError(exchange, HttpStatus.FORBIDDEN, null);
				}

			} catch (Exception e) {
				return this.onError(exchange, HttpStatus.FORBIDDEN, e);
			}

			this.updateRequest(exchange, token);
		}
		return chain.filter(exchange);
	}

	private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, Exception e) {

		try {
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(httpStatus);
			ErrorResponse res = new ErrorResponse();
			res.setErrorCode(httpStatus.value());
			res.setError(httpStatus.name());
			if (e != null)
				res.setDescription(e.getMessage());
			else
				res.setDescription(httpStatus.name());

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(res);

			StringBuilder builder = new StringBuilder();
			builder.append(json);
			builder.setLength(builder.length());
			byte[] bytes = builder.toString().getBytes(StandardCharsets.UTF_8);
			DataBuffer buffer = response.bufferFactory().wrap(bytes);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response.writeWith(Mono.just(buffer));
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		exchange.getResponse().setStatusCode(BAD_REQUEST);
		return exchange.getResponse().setComplete();
	}

	private String getAuthHeader(ServerHttpRequest request) {
		return request.getHeaders().getOrEmpty("Authorization").get(0);
	}

	private boolean isAuthMissing(ServerHttpRequest request) {
		return !request.getHeaders().containsKey("Authorization");
	}

	private void updateRequest(ServerWebExchange exchange, String token) {
		Claims claims = jwtUtil.getAllClaimsFromToken(token);
		exchange.getRequest().mutate().header("email", String.valueOf(claims.get("email"))).build();
	}
}
