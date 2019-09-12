package com.ydhl.micro.api.dto.admin.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设置促销地区
 *
 * @Author: yangll
 * @Date: 2019/5/5 9:37
 * @Version: 1.0
 */
@ApiModel(value = "设置促销地区")
@Data
@ToString
public class SetSmsAreaDTO {

    @ApiModelProperty(value = "商品id集合")
    @NotNull(message = "请选择商品")
    private List<Long> idList;

    @ApiModelProperty(value = "促销地区")
    private List<String> smsAreaCodes;

}
