package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName LoginFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 11:37
 * @Version 1.0
 **/
@Component
public class LoginFeignClientFallback implements LoginFeignClient {
    @Override
    public HttpResultDTO login(LoginAdminDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
