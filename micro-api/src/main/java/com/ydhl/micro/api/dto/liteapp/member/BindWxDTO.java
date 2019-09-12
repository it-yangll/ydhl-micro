package com.ydhl.micro.api.dto.liteapp.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName BindWxDTO
 * @Description 后台获取微信openId绑定账号
 * @Author Ly
 * @Date 2019/5/29 14:13
 * @Version 1.0
 **/
@ApiModel(value = "后台获取微信openId绑定账号请求参数")
@Data
@ToString
public class BindWxDTO {

    @ApiModelProperty(required = true)
    @NotBlank(message = "参数[code]为空")
    private String code;

}
