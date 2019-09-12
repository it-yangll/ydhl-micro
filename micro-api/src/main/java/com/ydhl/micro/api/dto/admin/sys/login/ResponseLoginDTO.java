package com.ydhl.micro.api.dto.admin.sys.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName ResponseLoginDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 11:18
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "用户登录信息")
public class ResponseLoginDTO {

    @ApiModelProperty(value = "用户id",example = "1")
    private Long id;

    @ApiModelProperty(value = "登录名称",example = "admin")
    private String userName;

    @ApiModelProperty(value = "用户名",example = "管理员")
    private String name;

    @ApiModelProperty(value = "手机",example = "13422116710")
    private String mobile;

    @ApiModelProperty(value = "token",example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUz........")
    private String accessToken;

    @ApiModelProperty(value = "用户角色",example = "*")
    private List<String> roles;

    @ApiModelProperty(value = "用户权限",example = "*")
    private List<String> permissions;

}
