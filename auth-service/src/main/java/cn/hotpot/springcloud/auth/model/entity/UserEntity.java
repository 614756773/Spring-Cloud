package cn.hotpot.springcloud.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
@Data
@AllArgsConstructor
public class UserEntity {
    private String uid;
    private String password;
    private String username;
    private String role;
}
