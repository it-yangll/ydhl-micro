package com.ydhl.micro.api.dto.admin.sys.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 16:25
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "登录实体")
public class LoginAdminDTO {

    @ApiModelProperty(value = "登录名",example = "IT_Yang")
    @NotBlank(message = "登录名为空")
    @Size(min = 4, max = 18, message = "登录名长度范围4~18个字符")
    private String name;

    @ApiModelProperty(value = "密码",example = "passwd")
    @NotBlank(message = "密码为空")
    @Size(min = 6, max = 18, message = "密码长度范围6~18个字符")
    private String passwd;

}
