package com.ydhl.micro.api.dto.mall.member;

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
@ApiModel(value = "修改会员密码实体")
public class UpdatePwdDTO {

    @ApiModelProperty(value = "用户原密码", required = true, example = "123456")
    @NotBlank(message = "原密码为空")
    private String oldPwd;

    @ApiModelProperty(value = "用户新密码", required = true, example = "123456")
    @NotBlank(message = "新密码为空")
    @Size(min = 6, max = 18, message = "新密码长度范围6~18个字符")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "密码由字母数字组成")
    private String newPwd;

    @ApiModelProperty(value = "确认密码", required = true, example = "123456")
    @NotBlank(message = "确认密码为空")
    @Size(min = 6, max = 18, message = "确认密码长度范围6~18个字符")
    private String confirmPwd;

}
