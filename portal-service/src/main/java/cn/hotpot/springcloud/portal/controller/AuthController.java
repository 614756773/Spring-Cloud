package cn.hotpot.springcloud.portal.controller;

import cn.hotpot.springcloud.portal.fegin.AuthClient;
import cn.hotpot.springcloud.portal.model.param.LoginParam;
import cn.hotpot.springcloud.portal.model.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "用户认证")
public class AuthController {
    @Autowired
    private AuthClient authClient;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<UserVO> login(@Valid @RequestBody LoginParam param, HttpServletRequest request) {
        UserVO vo = authClient.login(param);
        request.getSession().setAttribute("user", vo);
        // TODO 引入redis，实现分布式session
        return ResponseEntity.ok(vo);
    }
}
