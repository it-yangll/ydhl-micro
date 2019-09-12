package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName SysLogApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 16:12
 * @Version 1.0
 **/
public interface SysLogApi {

    /** 分页条件查询 */
    @RequestMapping(value = "log/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchLogDTO searchDTO);

}
