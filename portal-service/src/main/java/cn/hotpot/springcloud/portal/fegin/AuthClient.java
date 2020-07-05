package cn.hotpot.springcloud.portal.fegin;

import cn.hotpot.springcloud.portal.model.param.LoginParam;
import cn.hotpot.springcloud.portal.model.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@FeignClient(value = "auth-service", fallback = AuthClientFallback.class)
public interface AuthClient {
    /**
     * 登录
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    UserVO login(@RequestBody LoginParam param);
}
