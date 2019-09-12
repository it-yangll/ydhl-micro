package com.ydhl.micro.api.dto.admin.goods.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
  *商品标签修改业务参数
  *@Author: yangll
  *@Date: 2019/5/5 9:38
  *@Version: 1.0
  */
@ApiModel("商品标签修改")
@Data
@ToString
public class ModifyLabelDTO {

    @ApiModelProperty(value = "商品标签主键", example = "1")
    @NotNull(message = "商品标签ID参数为空.")
    private Long id;

    @ApiModelProperty(value = "商品标签状态码", example = "1")
    private String code;

    @ApiModelProperty(value = "商品标签名称", example = "1")
    @NotBlank(message = "标签名称不允许为空")
    private String name;

}
