package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.post.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.post.SearchPostDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysPostFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:42:49
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "给职位分配角色接口")
public class SysPostClient {

    @Autowired
    private SysPostFeignClient feignClient;

    /** 分页条件查询 */
    @ApiOperation(value = "查询职位信息")
    @RequestMapping(value = "post/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@RequestBody SearchPostDTO searchDTO) {
        return feignClient.pageSearch(searchDTO);
    }

    /** 条件查询 */
    @ApiOperation(value = "职位信息条件查询")
    @RequestMapping(value = "post/data", method = RequestMethod.POST)
    public HttpResultDTO search(@RequestBody SearchPostDTO searchDTO) {
        return feignClient.search(searchDTO);
    }

    /** 分配角色 */
    @ApiOperation(value = "给职位分配角色")
    @RequestMapping(value = "post/assignRole", method = RequestMethod.POST)
    public HttpResultDTO assignRole(@Valid @RequestBody AssignRoleDTO dto) {
        return feignClient.assignRole(dto);
    }

}
