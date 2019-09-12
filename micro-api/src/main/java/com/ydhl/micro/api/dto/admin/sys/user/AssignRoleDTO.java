package com.ydhl.micro.api.dto.admin.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName AssignRoleDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 14:19
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "分配角色实体")
public class AssignRoleDTO {

    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    @NotNull(message = "请选择用户")
    private Long userId;

    @ApiModelProperty(value = "角色ID集合", required = true)
    @NotNull(message = "请选择角色")
    private List<Long> roleIds;

}
