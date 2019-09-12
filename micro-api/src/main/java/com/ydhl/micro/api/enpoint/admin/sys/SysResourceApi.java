package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysResourceApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:57
 * @Version 1.0
 **/
public interface SysResourceApi {

    /** 单条查询 */
    @RequestMapping(value = "resource/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    @RequestMapping(value = "resource/tree", method = RequestMethod.GET)
    HttpResultDTO resourceTree();

    /** 新增 */
    @RequestMapping(value = "resource/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateResourceDTO dto);

    /** 修改 */
    @RequestMapping(value = "resource/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyResourceDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "resource/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-冻结 */
    @RequestMapping(value = "resource/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@RequestBody List<Long> idList);

    /** 批量操作-启用 */
    @RequestMapping(value = "resource/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@RequestBody List<Long> idList);

}
