package com.ydhl.micro.api.dto.admin.sys.post;

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
public class ResponsePostDTO {

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "配置项代码")
    private String itemCode;

    @ApiModelProperty(value = "配置项值")
    private String itemValue;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "配置项名称：(是 ， 否)")
    private String presetName;

    @ApiModelProperty(value = "内置配置项1:是(true)，0：否(false)")
    private Boolean preset;

    @ApiModelProperty(value = "状态名称")
    private String stateName;

    @ApiModelProperty(value = "状态| NORMAL正常  FROZEN冻结 DELETED删除")
    private String state;

    @ApiModelProperty(value = "职位拥有的角色ID集合，以逗号分隔")
    private String roleIds;

    @ApiModelProperty(value = "角色名称按逗号分隔")
    private String roleNames;

}
