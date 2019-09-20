package com.ydhl.micro.base.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ResponseAddrDTO
 * @Description 会员收货地址
 * @Author Ly
 * @Date 2019/6/21 11:25
 * @Version 1.0
 **/
@ApiModel(value = "返回的会员收货地址")
@Data
public class ResponseAddrDTO {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "所在地区")
    private String areaCode;

    @ApiModelProperty(value = "所在地区名称")
    private String areaName;

    @ApiModelProperty(value = "收货地址")
    private String addr;
}
