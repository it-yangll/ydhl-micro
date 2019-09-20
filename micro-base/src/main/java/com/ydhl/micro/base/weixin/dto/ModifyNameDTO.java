package com.ydhl.micro.base.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ModifyNameDTO
 * @Description 小程序修改用户名
 * @Author Ly
 * @Date 2019/5/26 11:01
 * @Version 1.0
 **/
@ApiModel(value = "会员信息修改请求参数")
@Data
@ToString
public class ModifyNameDTO {

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名为空")
    @Length(max = 50, message = "姓名请控制在50个字符以内")
    private String name;

}
