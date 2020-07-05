//package cn.hotpot.springcloud.gateway.config;
//
//import cn.hotpot.springcloud.gateway.ApplicationHelper;
//import cn.hotpot.springcloud.gateway.fegin.AuthClient;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @author qinzhu
// * @since 2020/7/2
// */
//@Configuration
//public class CustomFilter implements GatewayFilter, Ordered {
//
//    private static final String AUTHORIZE_TOKEN = "AUTHORIZE_TOKEN";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//
//        if (specialRequest(request)) {
//            return chain.filter(exchange);
//        }
//
//        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(AUTHORIZE_TOKEN);
//        if (token == null || !validToken(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
//        return chain.filter(exchange);
//    }
//
//    /**
//     * 调用认证服务，检查token是否无效
//     */
//    private Boolean validToken(String token) {
//        AuthClient authClient = ApplicationHelper.getBean(AuthClient.class);
//        return authClient.check(token);
//    }
//
//    /**
//     * 特殊的请求不需要校验token
//     */
//    private boolean specialRequest(ServerHttpRequest request) {
//        if (request.getURI().getPath().contains("login")) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes().route(r ->
//                r.path("/portal/**")
//                        // lb://portal-service中的portal-service是微服务在注册中心的名字
//                        .uri("lb://portal-service")
//                        .filters(new CustomFilter())
//                        .id("portal-service"))
//                .build();
//    }
//
//}
