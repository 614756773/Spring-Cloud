package cn.hotpot.springcloud.gateway.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@FeignClient(value = "auth-service", fallback = AuthClientFallback.class)
public interface AuthClient {

    /**
     * 检查token是否有效
     */
    @GetMapping("/auth/check")
    Boolean check(@RequestParam String token);
}
