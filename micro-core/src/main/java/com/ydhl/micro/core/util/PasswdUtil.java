package com.ydhl.micro.core.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.MD5;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName PasswdUtil
 * @Description 系统密码工具
 * @Author yangll
 * @Date 2019-9-10 11:42:35
 * @Version 1.0
 **/
public class PasswdUtil {

    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static String generatePasswd(String originalPwd, String salt) {
        MD5 md5 = new MD5(StringUtils.reverse(salt.toUpperCase()).getBytes());
        return StringUtils.reverse(HexUtil.encodeHexStr(md5.digest(StringUtils.reverse(originalPwd).getBytes()))).toUpperCase();
    }

    /**
     * 返回系统默认的初始密码
     *
     * @return
     */
    public static String getResetPasswd(String mobile) {
        return StringUtils.isNotBlank(mobile) ? StringUtils.rightPad(mobile, 6, "0") : "123456";
    }

}
