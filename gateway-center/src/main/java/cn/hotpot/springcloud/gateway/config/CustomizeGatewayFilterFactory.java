package cn.hotpot.springcloud.gateway.config;

import cn.hotpot.springcloud.gateway.ApplicationHelper;
import cn.hotpot.springcloud.gateway.fegin.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/7/2
 */
@Component
@Slf4j
public class CustomizeGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomizeGatewayFilterFactory.Config> {
    private static final String AUTHORIZE_TOKEN = "AUTHORIZE_TOKEN";

    public CustomizeGatewayFilterFactory() {
        super(Config.class);
        log.info("加载自定义AbstractGatewayFilterFactory：CustomizeGatewayFilterFactory");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(CustomizeGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (specialRequest(request)) {
                return chain.filter(exchange);
            }

            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            if (token == null || !validToken(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 调用认证服务，检查token是否无效
     */
    private Boolean validToken(String token) {
        AuthClient authClient = ApplicationHelper.getBean(AuthClient.class);
        return authClient.check(token);
    }

    /**
     * 特殊的请求不需要校验token
     */
    private boolean specialRequest(ServerHttpRequest request) {
        if (request.getURI().getPath().contains("login")) {
            return true;
        }
        return false;
    }

    public static class Config {
        // 控制是否开启认证
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
