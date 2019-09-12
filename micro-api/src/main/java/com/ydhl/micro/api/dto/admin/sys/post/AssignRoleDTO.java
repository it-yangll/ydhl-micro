package com.ydhl.micro.api.dto.admin.sys.post;

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
public class AssignRoleDTO {

    @NotNull(message = "请选择职位")
    private Long postId;

    @NotNull(message = "请选择角色")
    private List<Long> roleIds;

}
