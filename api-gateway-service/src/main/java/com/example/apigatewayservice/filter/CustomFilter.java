package com.example.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

	public CustomFilter() {
		super(Config.class);
	}

	public static class Config {
		// empty config
	}

	@Override
	public GatewayFilter apply(final CustomFilter.Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			log.info("Custom Pre filter: request id : {}", request.getId());
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("Custom Post filter: response code : {}", response.getStatusCode());
			}));
		};
	}
}
