package com.example.gateway.filter;

import com.example.gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreFilter implements GlobalFilter, Ordered {

  private final AuthService authService;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("First Pre Global Filter. Authorization check for the requested API");

    authService.validateAuthorization(exchange.getRequest().getHeaders());

    return chain.filter(exchange)
        .then(Mono.fromRunnable(() -> {
          log.info("Last Post Global Filter");
        }));
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
