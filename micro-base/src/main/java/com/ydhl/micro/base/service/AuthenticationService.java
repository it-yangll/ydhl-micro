package com.ydhl.micro.base.service;

import com.ydhl.micro.api.dto.admin.sys.NavTreeDTO;
import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.admin.sys.role.RoleResourceTreeDTO;
import com.ydhl.micro.base.entity.SysUser;

import java.util.List;

/**
 * @ClassName AuthenticationService
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/29 19:34
 * @Version 1.0
 **/
public interface AuthenticationService {

    /** 运营平台登录 */
    ResponseLoginDTO login(LoginAdminDTO dto);

    /** 用户菜单 */
    List<NavTreeDTO> navTree();

    /** 角色资源 */
    List<RoleResourceTreeDTO> roleResourceTree(Long roleId);

    /** 用户权限 */
    List<String> getUserPermissions(SysUser user);

    /** 用户角色 */
    List<String> getRoles(SysUser user);

}
