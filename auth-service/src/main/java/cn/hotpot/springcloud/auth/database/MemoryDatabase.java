package cn.hotpot.springcloud.auth.database;

import cn.hotpot.springcloud.auth.model.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/7/3
 */
public class MemoryDatabase {
    private static final Map<String, UserEntity> DATA;

    static {
        DATA = new HashMap<>(16);
        DATA.put("admin", new UserEntity("admin", "123456", "混合动力火锅", Role.ADMIN.name()));
        DATA.put("1001", new UserEntity("1001", "123456", "小赵", Role.COMMON.name()));
        DATA.put("1002", new UserEntity("1002", "123456", "小钱", Role.COMMON.name()));
        DATA.put("1003", new UserEntity("1003", "123456", "小孙", Role.COMMON.name()));
        DATA.put("1004", new UserEntity("1004", "123456", "小李", Role.COMMON.name()));
    }

    public static UserEntity queryByUid(String uid) {
        return DATA.get(uid);
    }

    enum Role {
        /**
         * 超级管理员
         */
        ADMIN,

        /**
         * 普通用户
         */
        COMMON
    }
}
