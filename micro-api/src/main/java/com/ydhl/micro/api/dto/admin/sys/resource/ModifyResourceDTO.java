package com.ydhl.micro.api.dto.admin.sys.resource;

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
public class ModifyResourceDTO {

    @NotNull(message = "ID为空")
    private Long id;

    @NotBlank(message = "资源名称为空")
    @Length(max = 100, message = "资源名称请控制在100个字符以内")
    private String name;

    @NotBlank(message = "资源权限描述符为空")
    @Length(max = 100, message = "资源权限描述符请控制在100个字符以内")
    private String permission;

    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @Length(max = 255, message = "资源地址请控制在255个字符以内")
    private String resourceUrl;

    @Length(max = 255, message = "图标请控制在255个字符以内")
    private String menuClass;

    @NotBlank(message = "资源类型为空")
    private String resourceType;

    private Integer sort;

}
