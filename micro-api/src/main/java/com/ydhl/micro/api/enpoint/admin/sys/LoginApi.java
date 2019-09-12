package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @ClassName AuthenticationApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 11:25
 * @Version 1.0
 **/
public interface LoginApi {

    /** 运营平台登录 */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    HttpResultDTO login(@Valid @RequestBody LoginAdminDTO dto);

}
