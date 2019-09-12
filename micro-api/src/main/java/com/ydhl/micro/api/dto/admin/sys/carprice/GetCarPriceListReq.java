package com.ydhl.micro.api.dto.admin.sys.carprice;

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
@ApiModel(value = "查询车型价格配置请求参数")
@Data
public class GetCarPriceListReq {

    @ApiModelProperty(value = "车型", example = "FD30")
    private String carModel;

    @ApiModelProperty(value = "页码",required = true, example = "1")
    private Integer page;

    @ApiModelProperty(value = "每页记录数",required = true, example = "10")
    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
}
