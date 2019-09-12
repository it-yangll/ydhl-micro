package com.ydhl.micro.api.dto.mall.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName ForgetDTO
 * @Description 忘记密码请求参数
 * @Author Ly
 * @Date 2019/5/26 10:48
 * @Version 1.0
 **/
@ApiModel(value = "忘记密码请求参数")
@Data
@ToString
public class ForgetDTO {

    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank(message = "登录账号为空")
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$", message = "手机号格式错误")
    private String mobile;

    @ApiModelProperty(value = "短信验证码", required = true)
    @NotBlank(message = "短信验证码为空")
    private String captcha;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "密码为空")
    @Size(min = 6, max = 18, message = "密码长度范围6~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "密码由字母数字组成")
    private String passwd;

}
