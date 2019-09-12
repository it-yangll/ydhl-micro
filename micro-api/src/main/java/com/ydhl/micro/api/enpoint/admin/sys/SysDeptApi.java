package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.dept.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.CreateDeptDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.ModifyDeptDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDeptApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:57
 * @Version 1.0
 **/
public interface SysDeptApi {

    /** 单条查询 */
    @RequestMapping(value = "dept/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    @RequestMapping(value = "dept/tree", method = RequestMethod.GET)
    HttpResultDTO deptTree();

    @RequestMapping(value = "dept/list", method = RequestMethod.GET)
    HttpResultDTO deptList();

    /** 新增 */
    @RequestMapping(value = "dept/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateDeptDTO dto);

    /** 修改 */
    @RequestMapping(value = "dept/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyDeptDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "dept/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-冻结 */
    @RequestMapping(value = "dept/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@RequestBody List<Long> idList);

    /** 批量操作-启用 */
    @RequestMapping(value = "dept/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@RequestBody List<Long> idList);

    /** 分配角色 */
    @RequestMapping(value = "dept/assignRole", method = RequestMethod.POST)
    HttpResultDTO assignRole(@Valid @RequestBody AssignRoleDTO dto);

}
