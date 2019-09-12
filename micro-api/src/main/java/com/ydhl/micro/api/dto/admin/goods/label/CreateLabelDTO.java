package com.ydhl.micro.api.dto.admin.goods.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
  *商品标签添加业务参数
  *@Author: yangll
  *@Date: 2019/5/5 9:37
  *@Version: 1.0
  */
@ApiModel(value = "商品标签增加")
@Data
@ToString
public class CreateLabelDTO {

    @ApiModelProperty(value = "商品标签主键", example = "1")
    private Long id;

    @ApiModelProperty(value = "商品标签状态码", example = "1")
    @NotBlank(message = "标签代码不允许为空")
    private String code;

    @ApiModelProperty(value = "商品标签名称", example = "FT600")
    @NotBlank(message = "标签名不允许为空")
    private String name;


}
