package cn.hotpot.springcloud.auth.controller;

import cn.hotpot.springcloud.auth.database.MemoryDatabase;
import cn.hotpot.springcloud.auth.model.entity.UserEntity;
import cn.hotpot.springcloud.auth.model.param.LoginParam;
import cn.hotpot.springcloud.auth.model.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@RestController
@Api(tags = "认证")
public class AuthController {

    private static final Map<String, UserVO> MEMORY_CACHE = new HashMap<>(16);

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginParam param) {
        UserEntity user = MemoryDatabase.queryByUid(param.getUid());
        if (user == null || !user.getPassword().equals(param.getPassword())) {
            return ResponseEntity.badRequest().body("用户名或者密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        UserVO vo = new UserVO(user, token);
        MEMORY_CACHE.put(token, vo);
        return ResponseEntity.ok(vo);
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token) {
        UserVO remove = MEMORY_CACHE.remove(token);
        if (remove == null) {
            return ResponseEntity.badRequest().body("非法的token");
        }
        return ResponseEntity.badRequest().body("成功注销");
    }

    @ApiOperation("检查token是否有效")
    @GetMapping("/check")
    public ResponseEntity check(String token) {
        UserVO userVO = MEMORY_CACHE.get(token);
        return ResponseEntity.ok(userVO != null);
    }
}
