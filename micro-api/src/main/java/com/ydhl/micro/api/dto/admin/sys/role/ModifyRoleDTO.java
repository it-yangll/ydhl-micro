package com.ydhl.micro.api.dto.admin.sys.role;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName DemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
public class ModifyRoleDTO {

    @NotNull(message = "ID为空")
    private Long id;

    @NotBlank(message = "角色为空")
    @Length(max = 100, message = "角色请控制在100个字符以内")
    private String name;

    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

}
