package com.ydhl.micro.api.dto.admin.goods.category;

import com.ydhl.micro.api.dto.common.SearchDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  *商品类型分页查询业务参数
  *@Author: yangll
  *@Date: 2019/5/5 14:40
  *@Version: 1.0
  */
@Data
@ToString
public class SearchCategoryPageDTO extends SearchDTO {

    @ApiModelProperty(value = "商品类型名称", example = "1")
    private String name;

    @ApiModelProperty(value = "商品类型编码", example = "1")
    private String code;


}
