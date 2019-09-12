package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysCustomTableFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName SysCustomTableClient
 * @Description 显示隐藏表列
 * @Author yangll
 * @Date 2019-9-11 11:40:57
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "显示隐藏列接口")
public class SysCustomTableClient {

    @Autowired
    private SysCustomTableFeignClient feignClient;

    /** 单条查询 */
    @RequestMapping(value = "customTable/{tableId}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "tableId") String tableId) {
        return feignClient.findById(tableId);
    }

    /** 新增 */
    @RequestMapping(value = "customTable/add", method = RequestMethod.POST)
    HttpResultDTO customized(@ApiParam(value = "展示table列字符串", required = true) @Valid @RequestBody CreateCustomTableDTO dto) {
        return feignClient.customized(dto);
    }

}
