package com.ydhl.micro.api.dto.admin.sys.dict;

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
public class ResponseDicitemDTO {

    @ApiModelProperty(value = "主键编码")
    private Long id;

    @ApiModelProperty(value = "字典类型代码")
    private String typeCode;

    @ApiModelProperty(value = "配置项代码")
    private String itemCode;

    @ApiModelProperty(value = "配置项值")
    private String itemValue;

    @ApiModelProperty(value = "说明备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "内置配置项名称：1:是，0：否")
    private String presetName;

    @ApiModelProperty(value = "内置配置项1:是(true)，0：否(false)")
    private Boolean preset;

    @ApiModelProperty(value = "状态名称| 正常  冻结 删除")
    private String stateName;

    @ApiModelProperty(value = "状态| NORMAL正常  FROZEN冻结 DELETED删除")
    private String state;

}
