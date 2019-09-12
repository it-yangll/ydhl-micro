package com.ydhl.micro.api.dto.admin.sys.carprice;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
@ApiModel(value = "修改车型价格配置请求参数")
@JSONType(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
@Data
public class UpdateCarPriceReq {
    @ApiModelProperty(value = "id", example = "1")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "车型", example = "FD30")
    @NotBlank(message = "车型不能为空")
    private String carModel;

    @ApiModelProperty(value = "基础定价", example = "20000")
    private BigDecimal basePrice;

    @ApiModelProperty(value = "最低价格", example = "12000")
    private BigDecimal floorPrice;

    @ApiModelProperty(value = "最高价格", example = "30000")
    private BigDecimal ceilingPrice;

    @ApiModelProperty(value = "备注", example = "3.5吨托盘车")
    private String remark;

    @ApiModelProperty(value = "状态| NORMAL正常  FROZEN冻结 DELETED删除", example = "NORMAL")
    private String state;
}
