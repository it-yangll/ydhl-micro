package com.ydhl.micro.api.dto.admin.goods.label;

import com.ydhl.micro.api.dto.common.SearchDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  *商品标签分页查询业务参数
  *@Author: yangll
  *@Date: 2019/5/5 14:40
  *@Version: 1.0
  */
@ApiModel("商品标签查询")
@Data
@ToString
public class SearchLabelPageDTO extends SearchDTO {

    @ApiModelProperty(value = "商品标签名称", example = "1")
    private String name;

    @ApiModelProperty(value = "商品标签状态码", example = "1")
    private String code;


}
