package com.ydhl.micro.api.dto.admin.goods.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 商品类型
 *
 * @Author: yangll
 * @Date: 2019/5/5 9:57
 * @Version: 1.0
 */
@ApiModel(value = "商品查询DTO")
@Data
@ToString
public class ResponseCategoryDTO {

    @ApiModelProperty(value = "商品类型主键", example = "1")
    private Long id;

    @ApiModelProperty(value = "商品类型状态码", example = "1")
    private String code;

    @ApiModelProperty(value = "商品类型名称", example = "1")
    private String name;

    public Long getCategoryId() {
        return this.id;
    }

}
