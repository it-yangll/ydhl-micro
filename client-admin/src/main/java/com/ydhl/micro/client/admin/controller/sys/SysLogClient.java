package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysLogFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:42:27
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "系统操作日志接口")
public class SysLogClient {

    @Autowired
    private SysLogFeignClient feignClient;

    /** 分页条件查询 */
    @ApiOperation(value = "查询系统操作日志（分页）")
    @RequestMapping(value = "log/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@ApiParam(value = "带分页的查询条件") @RequestBody SearchLogDTO searchDTO) {
        return feignClient.pageSearch(searchDTO);
    }

}
