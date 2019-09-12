package com.ydhl.micro.api.dto.mall.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ResponseLoginDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 11:18
 * @Version 1.0
 **/
@ApiModel(value = "商城登录后返回的信息")
@Data
@ToString
public class ResponseLoginDTO {

    @ApiModelProperty(value = "会员ID")
    private Long id;

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "会员姓名")
    private String name;

    @ApiModelProperty(value = "会员手机号")
    private String mobile;

    @ApiModelProperty(value = "会员邮箱")
    private String mail;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所在地区")
    private String areaCode;

    @ApiModelProperty(value = "所在地区")
    private String areaName;

    @ApiModelProperty(value = "收货地址")
    private String addr;

    @ApiModelProperty(value = "令牌")
    private String accessToken;

}
