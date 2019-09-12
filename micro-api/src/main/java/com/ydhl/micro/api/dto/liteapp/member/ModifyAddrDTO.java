package com.ydhl.micro.api.dto.liteapp.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName ModifyAddrDTO
 * @Description 小程序
 * @Author Ly
 * @Date 2019/5/26 11:01
 * @Version 1.0
 **/
@ApiModel(value = "会员收货地址修改请求参数")
@Data
@ToString
public class ModifyAddrDTO {

    @ApiModelProperty(value = "所在地区", required = true)
    @NotBlank(message = "所在地区为空")
    private String areaCode;

    @ApiModelProperty(value = "联系地址", required = true)
    @NotBlank(message = "联系地址为空")
    @Length(max = 255, message = "联系地址请控制在255个字符以内")
    private String addr;

}
