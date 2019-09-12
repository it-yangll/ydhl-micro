package com.ydhl.micro.api.dto.admin.sys.dept;

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
@ApiModel(value = "返回的部门实体")
public class ResponseDeptDTO {

    @ApiModelProperty(value = "部门ID")
    private Long id;

    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;

    @ApiModelProperty(value = "部门ID路径")
    private String parentPath;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门编码")
    private String code;

    @ApiModelProperty(value = "部门联系人电话")
    private String tel;

    @ApiModelProperty(value = "部门联系人")
    private String contactMan;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "部门管理员名称")
    private String superMan;

    @ApiModelProperty(value = "部门管理员ID")
    private Long superId;

    @ApiModelProperty(value = "部门状态描述")
    private String stateName;

    @ApiModelProperty(value = "部门状态")
    private String state;

    @ApiModelProperty(value = "部门拥有的角色ID集合，以逗号分隔")
    private String roleIds;

    @ApiModelProperty(value = "部门拥有的角色集合，逗号分隔")
    private String roleNames;

}
