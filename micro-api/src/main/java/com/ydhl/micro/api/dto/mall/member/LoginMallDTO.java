package com.ydhl.micro.api.dto.mall.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginMallDTO
 * @Description 登录商城请求参数
 * @Author Ly
 * @Date 2019/3/23 16:25
 * @Version 1.0
 **/
@ApiModel(value = "登录商城请求参数")
@Data
@ToString
public class LoginMallDTO {

    @ApiModelProperty(value = "登录名或手机号", required = true)
    @NotBlank(message = "登录账号为空")
    @Size(min = 4, max = 18, message = "登录账号长度范围4~18个字符")
    private String name;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码为空")
    @Size(min = 6, max = 18, message = "密码长度范围6~18个字符")
    private String passwd;

    private String loginType;

}
