package com.ydhl.micro.api.dto.admin.sys.user;

import com.ydhl.micro.api.dto.common.SearchDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName SearchDemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 14:02
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "用户查询实体")
public class SearchUserDTO extends SearchDTO {

    @ApiModelProperty(value = "登录名，模糊查询")
    private String userName;

    @ApiModelProperty(value = "用户名，模糊查询")
    private String name;

    @ApiModelProperty(value = "手机号，模糊查询")
    private String mobile;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "职位ID")
    private Long postId;

}
