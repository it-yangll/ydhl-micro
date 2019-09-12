package com.ydhl.micro.api.dto.admin.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName DemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "修改用户密码实体")
public class UpdatePwdDTO {

    @ApiModelProperty(value = "用户原密码", required = true, example = "123456")
    @NotBlank(message = "原密码为空")
    private String oldPwd;

    @ApiModelProperty(value = "用户新密码", required = true, example = "123456")
    @Size(min = 6, max = 18, message = "新密码长度范围6~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "用户密码由大小写字母数字组成")
    private String newPwd;

    @ApiModelProperty(value = "确认密码", required = true, example = "123456")
    @Size(min = 6, max = 18, message = "确认密码长度范围6~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "用户密码由大小写字母数字组成")
    private String confirmPwd;

}
