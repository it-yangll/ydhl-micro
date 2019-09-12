package com.ydhl.micro.api.dto.admin.goods.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  *商品标签查询业务参数
  *@Author: yangll
  *@Date: 2019/5/5 14:40
  *@Version: 1.0
  */
@Data
@ToString
public class SearchLabelDTO {

    @ApiModelProperty(value = "商品标签名称", example = "1")
    private String name;


}
