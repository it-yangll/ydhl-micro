package com.ydhl.micro.base.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName WxLoginAppDTO
 * @Description 微信授权手机号登录小程序请求参数
 * @Author Ly
 * @Date 2019/3/23 16:25
 * @Version 1.0
 **/
@ApiModel(value = "微信授权手机号登录小程序请求参数")
@Data
@ToString
public class WxLoginAppDTO {

    @ApiModelProperty(required = true)
    @NotBlank(message = "参数[encryptedData]为空")
    private String encryptedData;

    @ApiModelProperty(required = true)
    @NotBlank(message = "参数[iv]为空")
    private String iv;

    @ApiModelProperty(required = true)
    @NotBlank(message = "参数[openid]为空")
    private String openid;

}
