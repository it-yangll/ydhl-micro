package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.role.AssignResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.role.CreateRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.role.ModifyRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.role.SearchRoleDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysRoleApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 14:54
 * @Version 1.0
 **/
public interface SysRoleApi {

    /** 角色资源树 */
    @RequestMapping(value = "roleResourceTree", method = RequestMethod.GET)
    HttpResultDTO roleResourceTree(@RequestParam(name = "id") Long id);

    /** 单条查询 */
    @RequestMapping(value = "role/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    /** 分页条件查询 */
    @RequestMapping(value = "role/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchRoleDTO searchDTO);

    /** 条件查询 */
    @RequestMapping(value = "role/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchRoleDTO searchDTO);

    /** 新增 */
    @RequestMapping(value = "role/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateRoleDTO dto);

    /** 修改 */
    @RequestMapping(value = "role/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyRoleDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "role/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-冻结 */
    @RequestMapping(value = "role/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@RequestBody List<Long> idList);

    /** 批量操作-启用 */
    @RequestMapping(value = "role/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@RequestBody List<Long> idList);

    /** 分配菜单 */
    @RequestMapping(value = "role/assignResource", method = RequestMethod.POST)
    HttpResultDTO assignResource(@Valid @RequestBody AssignResourceDTO dto);
}
