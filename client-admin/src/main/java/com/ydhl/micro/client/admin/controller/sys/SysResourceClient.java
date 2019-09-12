package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysResourceFeignClient;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysResourceClient
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:43:00
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "资源管理相关接口")
public class SysResourceClient {

    @Autowired
    private SysResourceFeignClient feignClient;

    /** 单条查询 */
    @RequestMapping(value = "resource/{id}", method = RequestMethod.GET)
    public HttpResultDTO findById(@PathVariable Long id) {
        return feignClient.findById(id);
    }

    @RequestMapping(value = "resource/tree", method = RequestMethod.GET)
    public HttpResultDTO resourceTree() {
        return feignClient.resourceTree();
    }

    /** 新增 */
    @RequestMapping(value = "resource/add", method = RequestMethod.POST)
    public HttpResultDTO create(@Valid @RequestBody CreateResourceDTO dto) {
        return feignClient.create(dto);
    }

    /** 修改 */
    @RequestMapping(value = "resource/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@Valid @RequestBody ModifyResourceDTO dto) {
        return feignClient.modify(dto);
    }

    /** 批量操作-删除 */
    @RequestMapping(value = "resource/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@RequestBody List<Long> idList) {
        return feignClient.delete(idList);
    }

    /** 批量操作-冻结 */
    @RequestMapping(value = "resource/frozen", method = RequestMethod.POST)
    public HttpResultDTO frozen(@RequestBody List<Long> idList) {
        return feignClient.frozen(idList);
    }

    /** 批量操作-启用 */
    @RequestMapping(value = "resource/enable", method = RequestMethod.POST)
    public HttpResultDTO enable(@RequestBody List<Long> idList) {
        return feignClient.enable(idList);
    }

}
