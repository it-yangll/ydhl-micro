package com.ydhl.micro.gateway.consts;

import java.text.MessageFormat;

/**
 * @ClassName RedisKeyEnum
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:37:46
 * @Version 1.0
 **/
public class RedisKeyConst {

    private static final String USER_TOKEN_KEY = "sys:token:{0}:{1}:{2}";

    public static final String getUserTokenKey(String name, LoginType loginType, UserType userType) {
        return MessageFormat.format(USER_TOKEN_KEY, loginType, userType, name);
    }

}
