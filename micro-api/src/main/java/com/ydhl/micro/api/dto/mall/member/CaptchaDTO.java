package com.ydhl.micro.api.dto.mall.member;

import com.ydhl.micro.api.enumcode.consts.CaptchaMoudleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName CaptchaDTO
 * @Description 发送验证码
 * @Author Ly
 * @Date 2019/5/26 11:59
 * @Version 1.0
 **/
@ApiModel(value = "发送验证码请求参数")
@Data
@ToString
public class CaptchaDTO {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$", message = "手机号格式错误")
    private String mobile;

    @ApiModelProperty(value = "验证码类型[MALL_REGISTER:会员注册,MALL_FORGET:忘记密码]", required = true)
    private CaptchaMoudleEnum moudle;



}
