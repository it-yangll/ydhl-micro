package com.ydhl.micro.base.service;

import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ResourceTreeDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ResponseResourceDTO;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:29
 * @Version 1.0
 **/
public interface SysResourceService {

    List<ResponseResourceDTO> findAll();

    /** 单条查询 */
    ResponseResourceDTO findById(Long id);

    /** 部门树 */
    List<ResourceTreeDTO> resourceTree();

    /** 新增 */
    void create(CreateResourceDTO dto);

    /** 修改 */
    void modify(ModifyResourceDTO dto);

    /** 批量操作-删除 */
    void delete(List<Long> idList);

    /** 批量操作-冻结 */
    void frozen(List<Long> idList);

    /** 批量操作-启用 */
    void enable(List<Long> idList);

}
