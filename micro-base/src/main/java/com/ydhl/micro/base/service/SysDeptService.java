package com.ydhl.micro.base.service;

import com.ydhl.micro.api.dto.admin.sys.dept.*;
import com.ydhl.micro.api.dto.admin.sys.user.ResponseUserDTO;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:29
 * @Version 1.0
 **/
public interface SysDeptService {

    List<ResponseDeptDTO> findAll();

    /** 单条查询 */
    ResponseDeptDTO findById(Long id);

    /** 部门树 */
    List<DeptTreeDTO> deptTree();

    /** 部门列表 */
    List<ResponseDeptDTO> deptList();

    /** 新增 */
    void create(CreateDeptDTO dto);

    /** 修改 */
    void modify(ModifyDeptDTO dto);

    /** 批量操作-删除 */
    void delete(List<Long> idList);

    /** 批量操作-冻结 */
    void frozen(List<Long> idList);

    /** 批量操作-启用 */
    void enable(List<Long> idList);

    /** 分配角色 */
    void assignRole(AssignRoleDTO dto);

}
