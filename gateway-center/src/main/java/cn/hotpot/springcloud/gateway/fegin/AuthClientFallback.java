package cn.hotpot.springcloud.gateway.fegin;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@Slf4j
public class AuthClientFallback implements AuthClient {
    @Override
    public Boolean check(String token) {
        log.info("认证服务不可用，触发降级");
        return false;
    }
}
