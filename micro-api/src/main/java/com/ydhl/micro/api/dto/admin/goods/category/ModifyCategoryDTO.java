package com.ydhl.micro.api.dto.admin.goods.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
  *商品修改业务参数
  *@Author: yangll
  *@Date: 2019/5/5 9:38
  *@Version: 1.0
  */
@ApiModel("商品修改")
@Data
@ToString
public class ModifyCategoryDTO {

    @ApiModelProperty(value = "商品类型主键", example = "1")
    @NotNull(message = "商品类型ID参数为空.")
    private Long id;

    @ApiModelProperty(value = "商品类型状态码", example = "1")
    @NotBlank(message = "商品类型代码不允许为空.")
    private String code;

    @ApiModelProperty(value = "商品类型名称", example = "1")
    @NotBlank(message = "商品类型名称不允许为空.")
    private String name;

}
