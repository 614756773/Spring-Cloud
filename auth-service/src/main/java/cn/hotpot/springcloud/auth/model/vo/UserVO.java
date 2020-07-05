package cn.hotpot.springcloud.auth.model.vo;

import cn.hotpot.springcloud.auth.model.entity.UserEntity;
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

    public UserVO(UserEntity user, String token) {
        this.uid = user.getUid();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.token = token;
    }
}
