package com.ydhl.micro.base.service;

import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;

/**
 * @ClassName SysCustomTableService
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/8 12:56
 * @Version 1.0
 **/
public interface SysCustomTableService {

    String findById(String tableId);

    void customized(CreateCustomTableDTO dto);

}
