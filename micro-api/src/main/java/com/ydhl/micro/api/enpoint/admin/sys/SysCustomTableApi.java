package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @ClassName SysCustomTableApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/8 12:48
 * @Version 1.0
 **/
public interface SysCustomTableApi {

    /** 单条查询 */
    @RequestMapping(value = "customTable/{tableId}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "tableId") String tableId);

    /** 新增 */
    @RequestMapping(value = "customTable/add", method = RequestMethod.POST)
    HttpResultDTO customized(@ApiParam(value = "展示table列字符串", required = true) @Valid @RequestBody CreateCustomTableDTO dto);

}
