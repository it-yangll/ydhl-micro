package com.ydhl.micro.api.dto.admin.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName DemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "修改用户实体")
public class ModifyUserDTO {

    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    @NotNull(message = "ID为空")
    private Long id;

    @ApiModelProperty(value = "姓名", required = true, example = "测试用户1")
    @NotBlank(message = "姓名为空")
    @Length(max = 50, message = "姓名长度请控制在50个字符以内")
    private String name;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$", message = "手机号格式错误")
    private String mobile;

    @ApiModelProperty(value = "头像")
    @Length(max = 500, message = "头像请控制在500个字符以内")
    private String logo;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 100, message = "邮箱请控制在100个字符以内")
    @Email
    private String mail;

    @ApiModelProperty(value = "备注")
    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "职位ID")
    private Long postId;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "是否是部门管理员")
    private Boolean deptSuper = false;

}
