package com.example.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

	public LoggingFilter() {
		super(LoggingFilter.Config.class);
	}

	@Data
	public static class Config {
	}

	@Override
	public GatewayFilter apply(final LoggingFilter.Config config) {
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			log.info("Logging Pre filter: request id : {}", request.getId());
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("Logging  Post filter: response code : {}", response.getStatusCode());
			}));
		}, Ordered.HIGHEST_PRECEDENCE);

		return filter;
	}
}
