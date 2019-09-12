package com.ydhl.micro.api.dto.admin.sys.carprice;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: yangll
 * @description
 * @Date: 2019/4/29
 * @Version 1.0
 */
@ApiModel(value = "修改车型价格配置请求参数")
@JSONType(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
@Data
public class UpdateCarConfigPriceReq {
    @ApiModelProperty(value = "id", example = "1")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "状态| NORMAL正常  FROZEN冻结 DELETED删除", example = "NORMAL")
    private String state;
}
