package com.ydhl.micro.api.dto.mall.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName RegisterDTO
 * @Description 会员注册
 * @Author Ly
 * @Date 2019/5/26 10:48
 * @Version 1.0
 **/
@ApiModel(value = "会员注册请求参数")
@Data
@ToString
public class RegisterDTO {

    @ApiModelProperty(value = "短信验证码", required = true)
    @NotBlank(message = "短信验证码为空")
    private String captcha;

    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank(message = "登录账号为空")
    @Size(min = 4, max = 18, message = "登录账号长度范围4~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9_]*", message = "登录账号由字母数字下划线组成")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码为空")
    @Size(min = 6, max = 18, message = "密码长度范围6~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "密码由字母数字组成")
    private String password;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名为空")
    @Length(max = 50, message = "姓名请控制在50个字符以内")
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$", message = "手机号格式错误")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 100, message = "邮箱请控制在100个字符以内")
    @Email
    private String mail;

    @ApiModelProperty(value = "备注")
    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "所在地区", required = true)
    @NotBlank(message = "所在地区为空")
    private String areaCode;

    @ApiModelProperty(value = "联系地址", required = true)
    @NotBlank(message = "联系地址为空")
    @Length(max = 255, message = "联系地址请控制在255个字符以内")
    private String addr;
}
