package com.ydhl.micro.api.dto.admin.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
  *商品查询业务参数
  *@Author: yangll
  *@Date: 2019/5/5 14:40
  *@Version: 1.0
  */
@Data
@ToString
public class SearchGoodsDTO {

    @ApiModelProperty(value = "商品种类ID", example = "9127487128481")
    @Length(message = "商品类型长度步已超上限",max = 19)
    private Long categoryId;

    @ApiModelProperty(value = "车架号", example = "A18026888")
    private String vin;

    @ApiModelProperty(value = "商品名称", example = "SR600重型叉车")
    private String name;

    @ApiModelProperty(value = "商品状态0:未上架，1：已上架，2：已售出", example = "0")
    private Integer state;


}
