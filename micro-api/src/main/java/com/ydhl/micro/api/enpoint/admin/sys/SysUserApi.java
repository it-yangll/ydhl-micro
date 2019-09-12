package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.user.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysUserApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 12:35
 * @Version 1.0
 **/
public interface SysUserApi {

    /** 用户菜单树 */
    @RequestMapping(value = "navTree", method = RequestMethod.GET)
    HttpResultDTO navTree();

    /** 单条查询 */
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    /** 分页条件查询 */
    @RequestMapping(value = "user/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchUserDTO searchDTO);

    /** 条件查询 */
    @RequestMapping(value = "user/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchUserDTO searchDTO);

    /** 新增 */
    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateUserDTO dto);

    /** 修改 */
    @RequestMapping(value = "user/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyUserDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "user/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-冻结 */
    @RequestMapping(value = "user/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@RequestBody List<Long> idList);

    /** 批量操作-启用 */
    @RequestMapping(value = "user/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@RequestBody List<Long> idList);

    /** 批量操作-重置密码 */
    @RequestMapping(value = "user/reset", method = RequestMethod.POST)
    HttpResultDTO resetPwd(@RequestBody List<Long> idList);

    /** 分配角色 */
    @RequestMapping(value = "user/assignRole", method = RequestMethod.POST)
    HttpResultDTO assignRole(@Valid @RequestBody AssignRoleDTO dto);

    /** 修改密码 */
    @RequestMapping(value = "user/updatePwd", method = RequestMethod.POST)
    HttpResultDTO updatePwd(@Valid @RequestBody UpdatePwdDTO dto);

    /** 用户权限 */
    @RequestMapping(value = "user/permissions", method = RequestMethod.GET)
    List<String> getUserPermissions();

    /** 用户角色 */
    @RequestMapping(value = "user/roles", method = RequestMethod.GET)
    List<String> getUserRoles();

}
