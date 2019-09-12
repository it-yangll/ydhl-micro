package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.LoginFeignClient;
import com.ydhl.micro.client.admin.service.sys.SysRoleFeignClient;
import com.ydhl.micro.client.admin.service.sys.SysUserFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:38:55
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "登录加载项配置管理")
public class AuthenticationClient {

    @Autowired
    private LoginFeignClient loginFeignClient;
    @Autowired
    private SysUserFeignClient userFeignClient;
    @Autowired
    private SysRoleFeignClient roleFeignClient;


    /**
     * @param dto :
     * @return com.ydhl.micro.api.dto.common.ResponseDTO<com.ydhl.micro.api.dto.sys.UserDTO> :
     * @Description //管理系统登录
     * @Author Ly
     * @Date 2019/3/30 14:45
     **/
    @ApiOperation(value = "管理系统登录")
    @RequestMapping("login")
    public HttpResultDTO<ResponseLoginDTO> login(@RequestBody LoginAdminDTO dto) {
        log.info("管理系统登录:{}", dto.toString());
        return loginFeignClient.login(dto);
    }

    /** 导航菜单树 */
    //@ApiOperation(value = "导航菜单树")
    @RequestMapping("navTree")
    public HttpResultDTO navTree() {
        return userFeignClient.navTree();
    }

    /** 角色资源树 */
    //@ApiOperation(value = "角色资源树")
    @RequestMapping("roleResourceTree")
    public HttpResultDTO roleResourceTree(@RequestParam(name = "id") Long id) {
        return roleFeignClient.roleResourceTree(id);
    }
}
