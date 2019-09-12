package com.ydhl.micro.base.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.LoginApi;
import com.ydhl.micro.base.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 17:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class LoginController implements LoginApi {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public HttpResultDTO login(LoginAdminDTO dto) {
        return HttpResultDTO.ok(authenticationService.login(dto));
    }
}
