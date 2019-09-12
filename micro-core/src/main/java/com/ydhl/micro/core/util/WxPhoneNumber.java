package com.ydhl.micro.core.util;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName WxPhoneNumber
 * @Description 微信解密后的用户手机号
 * @Author yangll
 * @Date 2019-9-10 14:13:14
 * @Version 1.0
 **/
@Data
@ToString
public class WxPhoneNumber {

    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private String watermark;

    /** 数据水印 */
    @Data
    @ToString
    public class watermark {
        private String appid;
        private Integer timestamp;
    }

}
