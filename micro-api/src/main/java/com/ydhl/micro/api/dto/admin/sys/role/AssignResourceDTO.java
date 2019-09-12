package com.ydhl.micro.api.dto.admin.sys.role;

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
public class AssignResourceDTO {

    @NotNull(message = "请选择角色")
    private Long roleId;

    @NotNull(message = "请选择资源")
    private List<Long> resourceIds;

}
