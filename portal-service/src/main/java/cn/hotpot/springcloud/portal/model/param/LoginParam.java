package cn.hotpot.springcloud.portal.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@Data
public class LoginParam {
    @NotBlank(message = "账号不能为空")
    private String uid;

    @NotBlank(message = "密码不能为空")
    private String password;
}
