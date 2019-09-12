package com.ydhl.micro.client.admin.shiro;

import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.enumcode.consts.UserType;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.client.admin.service.sys.SysUserFeignClient;
import com.ydhl.micro.core.security.JwtBody;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MyShiroRealm
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:49:41
 * @Version 1.0
 **/
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private SysUserFeignClient userFeignClient;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        JwtBody jwtBody = JwtUtil.parseToJwtBody(principals.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if (StringUtils.equals(jwtBody.getUserType().name(), UserType.ADMIN.name())) {
            simpleAuthorizationInfo.addRole("*");
            simpleAuthorizationInfo.addStringPermission("*");
        } else {
            Set<String> userRoles = cacheHelper.getUserRoles(jwtBody.getIdentity());
            Set<String> userPermissions = cacheHelper.getUserPermissions(jwtBody.getIdentity());

            List<String> roles = null;
            List<String> permissions = null;

            if (CollectionUtil.isNotEmpty(userRoles)) {
                simpleAuthorizationInfo.setRoles(userRoles);
            } else {
                roles = userFeignClient.getUserRoles();
                log.info("重新查询用户【{}】角色:{}", jwtBody.getIdentity(), roles);
                simpleAuthorizationInfo.setRoles(new HashSet<>(roles));
            }
            if (CollectionUtil.isNotEmpty(userPermissions)) {
                simpleAuthorizationInfo.setStringPermissions(userRoles);
            } else {
                permissions = userFeignClient.getUserPermissions();
                log.info("重新查询用户【{}】权限:{}", jwtBody.getIdentity(), permissions);
                simpleAuthorizationInfo.setStringPermissions(new HashSet<>(permissions));
            }

            if (CollectionUtil.isNotEmpty(roles) || CollectionUtil.isNotEmpty(permissions)) {
                cacheHelper.saveUserPermission(jwtBody.getIdentity(), roles, permissions);
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //token的有效性已经在网关的Tokenfilter过滤器校验过了，这里直接认证通过
        String token = (String) auth.getCredentials();
        if (StringUtils.isBlank(token)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_UNAUTHORIZED, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_UNAUTHORIZED));
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
