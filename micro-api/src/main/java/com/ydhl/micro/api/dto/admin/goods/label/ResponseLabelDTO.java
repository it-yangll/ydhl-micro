package com.ydhl.micro.api.dto.admin.goods.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  *商品标签
  *@Author: yangll
  *@Date: 2019/5/5 9:57
  *@Version: 1.0
  */
@ApiModel(value = "商品标签查询DTO")
@Data
@ToString
public class ResponseLabelDTO {

    @ApiModelProperty(value = "商品标签主键", example = "1")
    private Long id;

    @ApiModelProperty(value = "商品标签状态码", example = "1")
    private String code;

    @ApiModelProperty(value = "商品标签名称", example = "1")
    private String name;

}
