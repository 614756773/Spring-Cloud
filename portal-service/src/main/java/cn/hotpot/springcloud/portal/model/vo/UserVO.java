package cn.hotpot.springcloud.portal.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@Data
@Accessors(chain = true)
public class UserVO {
    private String uid;
    private String username;
    private String role;
    private String token;
}
