package cn.hotpot.springcloud.portal.fegin;

import cn.hotpot.springcloud.portal.model.param.LoginParam;
import cn.hotpot.springcloud.portal.model.vo.UserVO;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
public class AuthClientFallback implements AuthClient {
    @Override
    public UserVO login(LoginParam param) {
        throw new RuntimeException("认证服务器展示不可用，触发fallback");
    }
}
