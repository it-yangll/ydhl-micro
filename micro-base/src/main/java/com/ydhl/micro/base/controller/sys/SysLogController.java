package com.ydhl.micro.base.controller.sys;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysLogApi;
import com.ydhl.micro.base.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysLogController
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 17:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysLogController implements SysLogApi {

    @Autowired
    private SysLogService service;

    @Override
    public HttpPageResultDTO pageSearch(SearchLogDTO searchDTO) {
        PageInfo pageInfo = service.pageSearch(searchDTO);
        return HttpPageResultDTO.ok(pageInfo.getList(), pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal());
    }
}
