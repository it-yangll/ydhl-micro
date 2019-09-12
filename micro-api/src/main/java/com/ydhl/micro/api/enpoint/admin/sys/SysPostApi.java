package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.post.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.post.SearchPostDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @ClassName SysPostApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:58
 * @Version 1.0
 **/
public interface SysPostApi {


    /** 分页条件查询 */
    @RequestMapping(value = "post/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchPostDTO searchDTO);

    /** 条件查询 */
    @RequestMapping(value = "post/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchPostDTO searchDTO);

    /** 分配角色 */
    @RequestMapping(value = "post/assignRole", method = RequestMethod.POST)
    HttpResultDTO assignRole(@Valid @RequestBody AssignRoleDTO dto);

}
