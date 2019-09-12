package com.ydhl.micro.base.service;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.post.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.post.ResponsePostDTO;
import com.ydhl.micro.api.dto.admin.sys.post.SearchPostDTO;

import java.util.List;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:14
 * @Version 1.0
 **/
public interface SysPostService {

    /** 分页条件查询 */
    PageInfo<ResponsePostDTO> pageSearch(SearchPostDTO searchDTO);

    /** 条件查询 */
    List<ResponsePostDTO> search(SearchPostDTO searchDTO);

    /** 分配角色 */
    void assignRole(AssignRoleDTO dto);

}
