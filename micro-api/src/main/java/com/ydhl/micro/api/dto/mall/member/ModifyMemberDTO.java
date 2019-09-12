package com.ydhl.micro.api.dto.mall.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName ModifyMemberDTO
 * @Description 商城
 * @Author Ly
 * @Date 2019/5/26 11:01
 * @Version 1.0
 **/
@ApiModel(value = "会员信息修改请求参数")
@Data
@ToString
public class ModifyMemberDTO {

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名为空")
    @Length(max = 50, message = "姓名请控制在50个字符以内")
    private String name;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 100, message = "邮箱请控制在100个字符以内")
    @Email
    private String mail;

    @ApiModelProperty(value = "备注")
    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "所在地区", required = true)
    @NotBlank(message = "所在地区为空")
    private String areaCode;

    @ApiModelProperty(value = "联系地址", required = true)
    @NotBlank(message = "联系地址为空")
    @Length(max = 255, message = "联系地址请控制在255个字符以内")
    private String addr;

}
