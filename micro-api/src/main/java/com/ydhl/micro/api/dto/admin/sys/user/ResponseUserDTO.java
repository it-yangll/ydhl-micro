package com.ydhl.micro.api.dto.admin.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName SearchDemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 14:02
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "返回的用户实体")
public class ResponseUserDTO {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "职位ID")
    private Long postId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "是否是部门管理员")
    private Boolean deptSuper = false;

    @ApiModelProperty(value = "用户拥有的角色ID集合，以逗号分隔")
    private String roleIds;

    @ApiModelProperty(value = "用户拥有的角色集合，以逗号分隔")
    private String roleNames;

    @ApiModelProperty(value = "用户状态描述")
    private String stateName;

    @ApiModelProperty(value = "用户状态")
    private String state;

    @ApiModelProperty(value = "用户类型描述")
    private String userTypeName;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "是否内置账号描述")
    private String presetName;

    @ApiModelProperty(value = "是否内置账号")
    private Boolean preset;

}
