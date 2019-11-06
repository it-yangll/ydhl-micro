package com.ydhl.micro.base.weixin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.admin.sys.user.CreateUserDTO;
import com.ydhl.micro.api.dto.liteapp.member.ResponseWxSessionDTO;
import com.ydhl.micro.api.dto.liteapp.member.WxCode2SessionDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.AuthType;
import com.ydhl.micro.api.enumcode.consts.CaptchaMoudleEnum;
import com.ydhl.micro.api.enumcode.consts.LoginType;
import com.ydhl.micro.api.enumcode.consts.UserType;
import com.ydhl.micro.api.enumcode.mall.MallCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysAuthRoleMapper;
import com.ydhl.micro.base.dao.auto.SysResourceMapper;
import com.ydhl.micro.base.dao.auto.SysRoleMapper;
import com.ydhl.micro.base.dao.auto.SysRoleResourceMapper;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.base.service.SysUserService;
import com.ydhl.micro.base.weixin.WeChatService;
import com.ydhl.micro.base.weixin.dto.BeanWxDTO;
import com.ydhl.micro.base.weixin.dto.BindWxDTO;
import com.ydhl.micro.base.weixin.util.WxConfigUtil;
import com.ydhl.micro.core.security.JwtBody;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.IpUtil;
import com.ydhl.micro.core.util.PayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @ClassName WeChatServiceImpl
 * @Description weixin
 * @Author yangll
 * @Date 2019/9/18 0018 17:10
 * @Version 1.0
 **/
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    public static final String WX_USER_REMARK = "微信授权访问用户";

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WxConfigUtil wxConfigUtil;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysUserService sysUserService;

    @Value("${token_exp_time}")
    private long tokenExpTime;

    @Value("${wx.code2session}")
    private String wxCode2SessionUrl;

    @Value("${wx.litewx.corpid}")
    private String wxCorpid;

    @Value("${wx.litewx.agentId}")
    private String agentId;

    @Value("${wx.litewx.corpsecret}")
    private String wxSecret;

    @Value("${wx.oauthCode}")
    private String oauthCodeUrl;

    @Value("${wx.encod}")
    private String encodUrl;

    @Override
    public String redirectWXAuthUrl() {
        return wxConfigUtil.redirectWXAuthUrl();
    }

    @Override
    public ResponseLoginDTO getWXUserInfo(String code, String state) {
        ResponseEntity<String> accountCode = wxConfigUtil.getWxUserInfo(code,state);
        if (!accountCode.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        String body = accountCode.getBody();
        BeanWxDTO.UserInfo userInfo = BeanWxDTO.getUserInfoBody(body);
        if(!userInfo.getErrcode().equals(0)){
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_USERID_CODE, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_USERID_CODE));
        }
        List<SysUser> sysUserList = sysUserService.searchByMobile(userInfo.getMobile());
        if(CollectionUtil.isNotEmpty(sysUserList)){
            ResponseLoginDTO result = new ResponseLoginDTO();
            BeanUtil.copyProperties(sysUserList.get(0), result);
            result.setAccessToken(JwtUtil.buildJWT(JwtBody.createJwtBody(UUID.randomUUID().toString(), tokenExpTime, IpUtil.getIpAddr(), LoginType.ADMIN, UserType.valueOf(sysUserList.get(0).getUserType()), sysUserList.get(0).getUserName(), sysUserList.get(0).getId(), sysUserList.get(0).getName())));
            result.setPermissions(getUserPermissions(sysUserList.get(0)));
            result.setRoles(getRoles(sysUserList.get(0)));
            return result;
        }else{
            throw new SystemRuntimeException(AdminCodeEnum.ERR_WX_PERMISSION_NOT_OPEN, cacheHelper.msgTemplate(AdminCodeEnum.ERR_WX_PERMISSION_NOT_OPEN));
        }
    }



    public void createBeanUser(BeanWxDTO.UserInfo userInfo){
        if(userInfo == null){
            throw new SystemRuntimeException(AdminCodeEnum.ERR_WX_USER_REPEAT, cacheHelper.msgTemplate(AdminCodeEnum.ERR_WX_USER_REPEAT));
        }
        CreateUserDTO userDto = new CreateUserDTO();
        userDto.setUserName(userInfo.getUserid());
        userDto.setName(userInfo.getName());
        userDto.setMobile(userInfo.getMobile());
        userDto.setLogo(userInfo.getAvatar());
        userDto.setMail(userInfo.getEmail());
        userDto.setRemark(WX_USER_REMARK);
        sysUserService.create(userDto);
    }





    @Override
    public ResponseEntity<String> getAccountCode() throws UnsupportedEncodingException {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(oauthCodeUrl,String.class,wxCorpid,URLEncoder.encode(encodUrl,"UTF-8"),agentId,PayUtil.createCode(6));

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        log.info("微信登录凭证：{}", wxResponse);
        //微信登录凭证放入redis
        //cacheHelper.saveWXBeanString(WXSecretDto.WX_CACHETOKEN_NAME, wxSecretDto, tokenExpTime);
        return wxResponse;
    }

    @Override
    public ResponseWxSessionDTO bindWx(BindWxDTO dto) {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(wxCode2SessionUrl, String.class, wxCorpid, wxSecret, dto.getCode());

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        WxCode2SessionDTO wxCode2SessionDTO = JSONObject.parseObject(wxResponse.getBody(),WxCode2SessionDTO.class);

        log.info("微信登录凭证：{}", wxResponse);
        if (wxCode2SessionDTO.getSession_key() == null) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }

        //微信登录凭证放入redis
        cacheHelper.saveWxSession(wxCode2SessionDTO.getOpenid(), wxCode2SessionDTO, tokenExpTime);
        return ResponseWxSessionDTO.transform(wxCode2SessionDTO);
    }


    /** 校验验证码是否正确 */
    private void checkCaptcha(String mobile, String code, CaptchaMoudleEnum captchaMoudleEnum) {
        String captcha = cacheHelper.getMobileCaptcha(mobile, captchaMoudleEnum);
        if (StringUtils.isBlank(captcha) || !StringUtils.equalsIgnoreCase(code, captcha)) {
            throw new SystemRuntimeException(MallCodeEnum.ERR_CAPTCHA, cacheHelper.msgTemplate(MallCodeEnum.ERR_CAPTCHA));
        }
    }



    public List<String> getUserPermissions(SysUser user) {
        List<String> result = new ArrayList<>();
        if (StringUtils.equalsIgnoreCase(user.getUserType(), UserType.ADMIN.name())) {
            result.add("*");
        } else {
            List<Long> userRoleIds = getUserRoleIds(user);
            if (CollectionUtil.isNotEmpty(userRoleIds)) {
                SysRoleExample exp = new SysRoleExample();
                exp.createCriteria().andIdIn(userRoleIds).andAvailableEqualTo(true);
                List<SysRole> roles = roleMapper.selectByExample(exp);
                if (CollectionUtil.isNotEmpty(roles)) {
                    List<Long> roleIds = new ArrayList<>();
                    roles.forEach(r -> {
                        roleIds.add(r.getId());
                    });

                    List<Long> userResourceIds = getResourceByRoles(roleIds);
                    if (CollectionUtil.isNotEmpty(userResourceIds)) {
                        SysResourceExample exp1 = new SysResourceExample();
                        exp1.createCriteria().andIdIn(userResourceIds).andAvailableEqualTo(true);
                        List<SysResource> resources = resourceMapper.selectByExample(exp1);
                        if (CollectionUtil.isNotEmpty(resources)) {
                            resources.forEach(r -> {
                                result.add(r.getPermission());
                            });
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<String> getRoles(SysUser user) {
        List<String> result = new ArrayList<>();
        if (StringUtils.equalsIgnoreCase(user.getUserType(), UserType.ADMIN.name())) {
            result.add("*");
        } else {
            List<Long> userRoleIds = getUserRoleIds(user);
            if (CollectionUtil.isNotEmpty(userRoleIds)) {
                SysRoleExample exp = new SysRoleExample();
                exp.createCriteria().andIdIn(userRoleIds).andAvailableEqualTo(true);
                List<SysRole> roles = roleMapper.selectByExample(exp);
                if (CollectionUtil.isNotEmpty(roles)) {
                    roles.forEach(r -> {
                        result.add(r.getRole());
                    });
                }
            }
        }
        return result;
    }

    private List<Long> getUserRoleIds(SysUser user) {
        List<Long> userRoleIds = new ArrayList<>();
        SysAuthRoleExample exp = new SysAuthRoleExample();
        exp.or().andAuthIdEqualTo(user.getId()).andAuthTypeEqualTo(AuthType.USER.name());
        if (user.getDeptId() != null) {
            exp.or().andAuthIdEqualTo(user.getDeptId()).andAuthTypeEqualTo(AuthType.DEPT.name());
        }
        if (user.getPostId() != null) {
            exp.or().andAuthIdEqualTo(user.getPostId()).andAuthTypeEqualTo(AuthType.POST.name());
        }
        List<SysAuthRole> authRoles = authRoleMapper.selectByExample(exp);
        if (CollectionUtil.isNotEmpty(authRoles)) {
            authRoles.forEach(r -> {
                userRoleIds.add(r.getRoleId());
            });
        }
        return userRoleIds;
    }

    private List<Long> getResourceByRoles(List<Long> roleIds) {
        List<Long> userResourceIds = new ArrayList<>();
        if (CollectionUtil.isEmpty(roleIds)) {
            return userResourceIds;
        }
        SysRoleResourceExample exp = new SysRoleResourceExample();
        exp.createCriteria().andRoleIdIn(roleIds);
        List<SysRoleResource> roleResources = roleResourceMapper.selectByExample(exp);
        if (CollectionUtil.isNotEmpty(roleResources)) {
            roleResources.forEach(r -> {
                userResourceIds.add(r.getResourceId());
            });
        }
        return userResourceIds;
    }



}
