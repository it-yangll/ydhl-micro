package com.ydhl.micro.base.service;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.role.*;
import com.ydhl.micro.base.entity.SysRole;

import java.util.List;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:14
 * @Version 1.0
 **/
public interface SysRoleService {

    /** 单条查询 */
    SysRole findById(Long id);

    /** 分页条件查询 */
    PageInfo<ResponseRoleDTO> pageSearch(SearchRoleDTO searchDTO);

    /** 条件查询 */
    List<ResponseRoleDTO> search(SearchRoleDTO searchDTO);

    /** 新增 */
    void create(CreateRoleDTO dto);

    /** 修改 */
    void modify(ModifyRoleDTO dto);

    /** 批量操作-删除 */
    void delete(List<Long> idList);

    /** 批量操作-冻结 */
    void frozen(List<Long> idList);

    /** 批量操作-启用 */
    void enable(List<Long> idList);

    /** 分配资源 */
    void assignResource(AssignResourceDTO dto);

}
