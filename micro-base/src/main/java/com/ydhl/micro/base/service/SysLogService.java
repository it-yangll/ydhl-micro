package com.ydhl.micro.base.service;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.base.entity.SysLog;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:14
 * @Version 1.0
 **/
public interface SysLogService {

    /** 分页条件查询 */
    PageInfo<SysLog> pageSearch(SearchLogDTO searchDTO);

}
