package com.ydhl.micro.api.dto.admin.sys.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName DemoDTO
 * @Description 新增
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "添加部门实体")
public class CreateDeptDTO {

    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;

    @ApiModelProperty(value = "部门名称", required = true, example = "测试部门1")
    @NotBlank(message = "部门名称为空")
    @Length(max = 500, message = "部门名称请控制在500个字符以内")
    private String name;

    @ApiModelProperty(value = "部门编码")
    @Length(max = 100, message = "部门编码请控制在100个字符以内")
    private String code;

    @ApiModelProperty(value = "电话")
    @Length(max = 15, message = "电话请控制在15个字符以内")
    private String tel;

    @ApiModelProperty(value = "联系人")
    @Length(max = 100, message = "联系人请控制在100个字符以内")
    private String contactMan;

    @ApiModelProperty(value = "备注")
    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
