package com.ydhl.micro.base.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysCustomTableApi;
import com.ydhl.micro.base.service.SysCustomTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysCustomTableController
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/8 12:56
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysCustomTableController implements SysCustomTableApi {

    @Autowired
    private SysCustomTableService service;

    @Override
    public HttpResultDTO findById(String tableId) {
        return HttpResultDTO.ok(service.findById(tableId));
    }

    @Override
    public HttpResultDTO customized(CreateCustomTableDTO dto) {
        service.customized(dto);
        return HttpResultDTO.ok();
    }

}
