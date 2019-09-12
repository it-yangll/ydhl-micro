package com.ydhl.micro.api.dto.liteapp.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginAppDTO
 * @Description 登录小程序请求参数
 * @Author Ly
 * @Date 2019/3/23 16:25
 * @Version 1.0
 **/
@ApiModel(value = "登录小程序请求参数")
@Data
@ToString
public class LoginAppDTO {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$", message = "手机号格式错误")
    private String mobile;

    @ApiModelProperty(value = "短信验证码", required = true)
    @NotBlank(message = "短信验证码为空")
    private String code;

    private String loginType;

}
