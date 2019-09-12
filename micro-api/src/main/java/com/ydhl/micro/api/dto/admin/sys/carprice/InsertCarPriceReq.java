package com.ydhl.micro.api.dto.admin.sys.carprice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: yangll
 * @description
 * @Date: 2019/4/29
 * @Version 1.0
 */
@Data
@ApiModel(value = "新增车型价格请求参数")
public class InsertCarPriceReq {
    @ApiModelProperty(value = "车型", example = "FD30")
    @NotBlank(message = "车型不能为空")
    private String carModel;

    @ApiModelProperty(value = "基础定价", example = "20000")
    @NotNull(message = "基础定价不能为空")
    private BigDecimal basePrice;

    @ApiModelProperty(value = "最低价格", example = "12000")
    @NotNull(message = "最低价格不能为空")
    private BigDecimal floorPrice;

    @ApiModelProperty(value = "最高价格", example = "30000")
    @NotNull(message = "最高价格不能为空")
    private BigDecimal ceilingPrice;

    @ApiModelProperty(value = "备注", example = "3.5吨托盘车")
    private String remark;
}
