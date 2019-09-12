package com.ydhl.micro.base.service;


import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.user.*;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:29
 * @Version 1.0
 **/
public interface SysUserService {

    /** 单条查询 */
    ResponseUserDTO findById(Long id);

    /** 分页条件查询 */
    PageInfo<ResponseUserDTO> pageSearch(SearchUserDTO searchDTO);

    /** 条件查询 */
    List<ResponseUserDTO> search(SearchUserDTO searchDTO);

    /** 新增 */
    void create(CreateUserDTO dto);

    /** 修改 */
    void modify(ModifyUserDTO dto);

    /** 批量操作-删除 */
    void delete(List<Long> idList);

    /** 批量操作-冻结 */
    void frozen(List<Long> idList);

    /** 批量操作-启用 */
    void enable(List<Long> idList);

    /** 批量操作-重置密码 */
    void resetPwd(List<Long> idList);

    /** 分配角色 */
    void assignRole(AssignRoleDTO dto);

    /** 修改密码 */
    void updatePwd(UpdatePwdDTO dto);
}
