package com.ydhl.micro.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.admin.goods.category.ResponseCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.label.ResponseLabelDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.ResponseDeptDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ResponseDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ResponseResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.role.ResponseRoleDTO;
import com.ydhl.micro.api.dto.liteapp.member.WxCode2SessionDTO;
import com.ydhl.micro.api.dto.liteapp.weixin.WXSecretBeanDto;
import com.ydhl.micro.api.dto.liteapp.weixin.WXSecretDto;
import com.ydhl.micro.api.dto.liteapp.weixin.WxBeanStringSuper;
import com.ydhl.micro.api.enumcode.CodeEnumClass;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.enumcode.consts.CaptchaMoudleEnum;
import com.ydhl.micro.api.enumcode.consts.LoginType;
import com.ydhl.micro.api.enumcode.consts.UserType;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.core.consts.RedisKeyConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheHelper
 * @Description redis 缓存管理
 * @Author yangll
 * @Date 2019-9-10 11:43:06
 * @Version 1.0
 **/
@Component
@Slf4j
public class CacheHelper {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param pattern :key正则
     * @return java.util.Set<java.lang.String> :
     * @Description //获取缓存key集合
     * @Author yangll
     * @Date 2019-9-10 11:43:26
     **/
    public Set<String> getKeys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * @param delKey :待删除的key
     * @return void :
     * @Description //删除缓存
     * @Author yangll
     * @Date 2019-9-10 11:43:39
     **/
    public void delete(String delKey) {
        stringRedisTemplate.delete(delKey);
    }

    /**
     * @param delKey :待删除的key集合
     * @return void :
     * @Description //删除缓存
     * @Author yangll
     * @Date 2019-9-10 11:43:51
     **/
    public void delete(Collection<String> delKey) {
        stringRedisTemplate.delete(delKey);
    }

    /**
     * @param userName  : 用户名
     * @param loginType : 登录类型
     * @param userType  : 用户类型
     * @param token     : 令牌
     * @param expTime   : 超时时间（秒）
     * @return void :
     * @Description //保存用户登录令牌
     * @Author yangll
     * @Date 2019-9-10 11:44:24
     **/
    public void saveUserToken(String userName, LoginType loginType, UserType userType, String token, long expTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getUserTokenKey(userName, loginType, userType), token, expTime, TimeUnit.SECONDS);
    }

    /**
     * @param openId :微信openId
     * @return com.ybzt.micro.api.dto.liteapp.member.WxCode2SessionDTO :
     * @Description //获取微信登录凭证
     * @Author yangll
     * @Date 2019-9-10 11:44:36
     **/
    public WxCode2SessionDTO getWxSession(String openId) {
        String wxCode2SessionDTO = stringRedisTemplate.opsForValue().get(RedisKeyConst.getWxSessionKey(openId));
        if (StringUtils.isBlank(wxCode2SessionDTO)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(wxCode2SessionDTO), WxCode2SessionDTO.class);
    }

    /**
     * @param openId :微信openId
     * @return com.ybzt.micro.api.dto.liteapp.member.WXSecretDto :
     * @Description //获取微信登录凭证
     * @Author yangll
     * @Date 2019-9-10 11:44:36
     **/
    public WXSecretBeanDto getWxAccessToken(String openId) {
        String wXSecretBeanDto = stringRedisTemplate.opsForValue().get(RedisKeyConst.getWxSessionKey(openId));
        if (StringUtils.isBlank(wXSecretBeanDto)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(wXSecretBeanDto), WXSecretBeanDto.class);
    }

    /*public WXSecretDto getWxAccessToken(String openId) {
        String wxSecretDto = stringRedisTemplate.opsForValue().get(RedisKeyConst.getWxSessionKey(openId));
        if (StringUtils.isBlank(wxSecretDto)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(wxSecretDto), WXSecretDto.class);
    }*/

    /**
     * @param openId            : 微信openId
     * @param wxCode2SessionDTO : 微信登录凭证
     * @param tokenExpTime      :失效时间（秒）
     * @return void :
     * @Description //保存微信登录凭证
     * @Author yangll
     * @Date 2019-9-10 11:44:53
     **/
    public void saveWxSession(String openId, WxCode2SessionDTO wxCode2SessionDTO, long tokenExpTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getWxSessionKey(openId), JSONObject.toJSONString(wxCode2SessionDTO), tokenExpTime, TimeUnit.SECONDS);
    }

    /**
     * @param openId            : 微信openId
     * @param wxSecretDto : 微信登录凭证
     * @param tokenExpTime      :失效时间（秒）
     * @return void :
     * @Description //保存微信登录凭证
     * @Author yangll
     * @Date 2019-9-10 11:44:53
     **/
    public void saveWXSession(String openId, WXSecretDto wxSecretDto, long tokenExpTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getWxSessionKey(openId), JSONObject.toJSONString(wxSecretDto), tokenExpTime, TimeUnit.SECONDS);
    }

    /**
     * @param userId            : 当前微信用户ID
     * @param wxDto : 当前用户信息
     * @param tokenExpTime      :失效时间（秒）
     * @return void :
     * @Description //保存微信登录凭证
     * @Author yangll
     * @Date 2019-10-17 11:28:45
     **/
    public void saveWXBeanString(String userId, WxBeanStringSuper wxDto, long tokenExpTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getWxSessionKey(userId), JSONObject.toJSONString(wxDto), tokenExpTime, TimeUnit.SECONDS);
    }

    /**
     * @param userId            : 当前微信用户ID
     * @param wxDto : 当前用户信息
     * @param tokenExpTime      :失效时间（秒）
     * @return void :
     * @Description //保存微信登录凭证
     * @Author yangll
     * @Date 2019-10-17 11:28:45
     **/
    public void getWXBeanString(String userId, WxBeanStringSuper wxDto, long tokenExpTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getWxSessionKey(userId), JSONObject.toJSONString(wxDto), tokenExpTime, TimeUnit.SECONDS);
    }

    /**
     * @param userName    : 用户账号
     * @param roles       : 用户角色集合
     * @param permissions : 用户权限集合
     * @return void :
     * @Description //保存用户操作权限
     * @Author yangll
     * @Date 2019-9-10 11:45:03
     **/
    public void saveUserPermission(String userName, List<String> roles, List<String> permissions) {
        JSONObject up = new JSONObject();
        up.put("roles", roles);
        up.put("permissions", permissions);
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getUserPermissionKey(userName), up.toJSONString());
    }

    /**
     * @return void :
     * @Description //删除缓存的用户权限
     * @Author yangll
     * @Date 2019-9-10 11:45:13
     **/
    public void deleteUserPermission() {
        log.info("从缓存中删除所有用户的权限");
        delete(getKeys("sys:user:permission:*"));
    }

    public void deleteUserPermission(String userName) {
        log.info("从缓存中删除用户[{}]的权限", userName);
        delete(RedisKeyConst.getUserPermissionKey(userName));
    }

    /**
     * @param userName : 用户账号
     * @return java.util.Set<java.lang.String> :
     * @Description //获取用户角色集合
     * @Author yangll
     * @Date 2019-9-10 11:45:24
     **/
    public Set<String> getUserRoles(String userName) {
        Set<String> userRoles = null;
        String userAuthInfo = stringRedisTemplate.opsForValue().get(RedisKeyConst.getUserPermissionKey(userName));
        if (StringUtils.isNotBlank(userAuthInfo)) {
            JSONObject authInfo = JSONObject.parseObject(userAuthInfo);
            JSONArray roles = authInfo.getJSONArray("roles");
            if (CollectionUtil.isNotEmpty(roles)) {
                roles.forEach(K -> {
                    userRoles.add(K.toString());
                });
            }
        }
        return userRoles;
    }

    /**
     * @param userName : 用户账号
     * @return java.util.Set<java.lang.String> :
     * @Description //获取用户权限集合
     * @Author yangll
     * @Date 2019-9-10 11:45:38
     **/
    public Set<String> getUserPermissions(String userName) {
        Set<String> userPermissions = null;
        String userAuthInfo = stringRedisTemplate.opsForValue().get(RedisKeyConst.getUserPermissionKey(userName));
        if (StringUtils.isNotBlank(userAuthInfo)) {
            JSONObject authInfo = JSONObject.parseObject(userAuthInfo);
            JSONArray roles = authInfo.getJSONArray("permissions");
            if (CollectionUtil.isNotEmpty(roles)) {
                roles.forEach(K -> {
                    userPermissions.add(K.toString());
                });
            }
        }
        return userPermissions;
    }

    /**
     * @param roleDTOList :角色集合
     * @return void :
     * @Description //缓存角色集合
     * @Author yangll
     * @Date 2019-9-10 11:45:48
     **/
    public void saveRoles(List<ResponseRoleDTO> roleDTOList) {
        if (CollectionUtil.isNotEmpty(roleDTOList)) {
            roleDTOList.forEach(r -> {
                saveRole(r);
            });
        }
    }

    /**
     * @param roleDTO :角色
     * @return void :
     * @Description //缓存角色
     * @Author yangll
     * @Date 2019-9-10 11:46:02
     **/
    public void saveRole(ResponseRoleDTO roleDTO) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.ROLE_KEY, roleDTO.getId().toString(), JSONObject.toJSONString(roleDTO));
    }

    /**
     * @param roleId :角色id
     * @return void :
     * @Description //删除角色
     * @Author yangll
     * @Date 2019-9-10 11:53:48
     **/
    public void deleteRole(Long roleId) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.ROLE_KEY, roleId.toString());
    }

    /**
     * @param roleId :角色ID
     * @return com.ybzt.micro.api.dto.admin.sys.role.ResponseRoleDTO :
     * @Description //获取角色缓存
     * @Author yangll
     * @Date 2019-9-10 11:53:58
     **/
    public ResponseRoleDTO getRole(Long roleId) {
        String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.ROLE_KEY, roleId.toString()).toString();
        return JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseRoleDTO.class);
    }

    /**
     * @param resourceList :资源集合
     * @return void :
     * @Description //缓存资源集合
     * @Author yangll
     * @Date 2019-9-10 11:54:08
     **/
    public void saveResources(List<ResponseResourceDTO> resourceList) {
        if (CollectionUtil.isNotEmpty(resourceList)) {
            resourceList.forEach(r -> {
                saveResource(r);
            });
        }
    }

    /**
     * @param resourceDTO :资源
     * @return void :
     * @Description //缓存资源
     * @Author yangll
     * @Date 2019-9-10 11:54:18
     **/
    public void saveResource(ResponseResourceDTO resourceDTO) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.RESOURCE_KEY, resourceDTO.getId().toString(), JSONObject.toJSONString(resourceDTO));
    }

    /**
     * @param resourceId :资源id
     * @return void :
     * @Description //删除资源缓存
     * @Author yangll
     * @Date 2019-9-10 11:54:28
     **/
    public void deleteResource(Long resourceId) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.RESOURCE_KEY, resourceId.toString());
    }

    /**
     * @param resourceId :资源id
     * @return com.ybzt.micro.api.dto.admin.sys.resource.ResponseResourceDTO :
     * @Description //获取资源缓存
     * @Author yangll
     * @Date 2019-9-10 11:54:39
     **/
    public ResponseResourceDTO getResource(Long resourceId) {
        String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.RESOURCE_KEY, resourceId.toString()).toString();
        return JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseResourceDTO.class);
    }

    /**
     * @param deptList :部门集合
     * @return void :
     * @Description //缓存部门集合
     * @Author yangll
     * @Date 2019-9-10 11:54:53
     **/
    public void saveDepts(List<ResponseDeptDTO> deptList) {
        if (CollectionUtil.isNotEmpty(deptList)) {
            deptList.forEach(r -> {
                saveDept(r);
            });
        }
    }

    /**
     * @param deptDTO :部门
     * @return void :
     * @Description //缓存部门
     * @Author yangll
     * @Date 2019-9-10 11:55:05
     **/
    public void saveDept(ResponseDeptDTO deptDTO) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.DEPT_KEY, deptDTO.getId().toString(), JSONObject.toJSONString(deptDTO));
    }

    /**
     * @param deptId :部门id
     * @return com.ybzt.micro.api.dto.admin.sys.dept.ResponseDeptDTO :
     * @Description //获取部门缓存
     * @Author yangll
     * @Date 2019-9-10 11:55:23
     **/
    public ResponseDeptDTO getDept(Long deptId) {
        String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.DEPT_KEY, deptId.toString()).toString();
        return JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseDeptDTO.class);
    }

    /**
     * @param dicitemDTOList :数据字典集合
     * @return void :
     * @Description //缓存数据字典
     * @Author yangll
     * @Date 2019-9-10 11:55:34
     **/
    public void saveDicts(List<ResponseDicitemDTO> dicitemDTOList) {
        if (CollectionUtil.isNotEmpty(dicitemDTOList)) {
            dicitemDTOList.forEach(r -> {
                saveDict(r);
            });
        }
    }

    /**
     * @param dicitemDTO :数据字典
     * @return void :
     * @Description //缓存数据字典
     * @Author yangll
     * @Date 2019/6/26 13:25
     **/
    public void saveDict(ResponseDicitemDTO dicitemDTO) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getDictItemIdKey(dicitemDTO.getId()), JSONObject.toJSONString(dicitemDTO));
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getDictItemCodeKey(dicitemDTO.getItemCode()), JSONObject.toJSONString(dicitemDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.getDictCodeMapKey(dicitemDTO.getTypeCode()), dicitemDTO.getItemCode(), JSONObject.toJSONString(dicitemDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.getDictIdMapKey(dicitemDTO.getTypeCode()), dicitemDTO.getId().toString(), JSONObject.toJSONString(dicitemDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.DICT_ITEM_CODE_MAP_KEY, dicitemDTO.getItemCode(), JSONObject.toJSONString(dicitemDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.DICT_ITEM_ID_MAP_KEY, dicitemDTO.getId().toString(), JSONObject.toJSONString(dicitemDTO));
    }

    /**
     * @param typeCode :数据字典类型
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO> :
     * @Description //根据数据字典类型获取数据字典集合（key=id）
     * @Author yangll
     * @Date 2019/6/26 13:50
     **/
    public Map<String, ResponseDicitemDTO> getDictIdMap(String typeCode) {
        Map<String, ResponseDicitemDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.getDictIdMapKey(typeCode));
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.getDictIdMapKey(typeCode), k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseDicitemDTO.class));
            });
        }
        return map;
    }

    /**
     * @param typeCode :数据字典类型
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO> :
     * @Description //根据数据字典类型获取数据字典集合（key=code）
     * @Author yangll
     * @Date 2019/6/26 13:50
     **/
    public Map<String, ResponseDicitemDTO> getDictCodeMap(String typeCode) {
        Map<String, ResponseDicitemDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.getDictCodeMapKey(typeCode));
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.getDictCodeMapKey(typeCode), k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseDicitemDTO.class));
            });
        }
        return map;
    }

    /**
     * @param typeCode : 数据字典类型
     * @param itemCode : 数据字典code
     * @return com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO :
     * @Description //获取数据字典
     * @Author yangll
     * @Date 2019/6/26 14:00
     **/
    public ResponseDicitemDTO getDictItemByCode(String typeCode, String itemCode) {
        String item = stringRedisTemplate.opsForHash().get(RedisKeyConst.getDictCodeMapKey(typeCode), itemCode).toString();
        return JSONObject.toJavaObject(JSONObject.parseObject(item), ResponseDicitemDTO.class);
    }

    /**
     * @param itemCode : 数据字典配置项代码
     * @return com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO :
     * @Description //获取数据字典
     * @Author yangll
     * @Date 2019/6/26 14:00
     **/
    public ResponseDicitemDTO getDictItemByCode(String itemCode) {
        String item = stringRedisTemplate.opsForValue().get(RedisKeyConst.getDictItemCodeKey(itemCode));
        return JSONObject.toJavaObject(JSONObject.parseObject(item), ResponseDicitemDTO.class);
    }

    /**
     * @param typeCode : 数据字典类型
     * @param itemId   : 数据字典id
     * @return com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO :
     * @Description //获取数据字典
     * @Author yangll
     * @Date 2019/6/26 14:00
     **/
    public ResponseDicitemDTO getDictItemById(String typeCode, String itemId) {
        String item = stringRedisTemplate.opsForHash().get(RedisKeyConst.getDictIdMapKey(typeCode), itemId).toString();
        return JSONObject.toJavaObject(JSONObject.parseObject(item), ResponseDicitemDTO.class);
    }

    /**
     * @param itemId : 数据字典id
     * @return com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO :
     * @Description //获取数据字典
     * @Author yangll
     * @Date 2019/6/26 14:00
     **/
    public ResponseDicitemDTO getDictItemById(Long itemId) {
        String item = stringRedisTemplate.opsForValue().get(RedisKeyConst.getDictItemIdKey(itemId));
        return JSONObject.toJavaObject(JSONObject.parseObject(item), ResponseDicitemDTO.class);
    }

    /**
     * @return java.util.Map<java.lang.String,com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO> :
     * @Description //获取数据字典集合（key=code）
     * @Author yangll
     * @Date 2019/6/27 15:35
     **/
    public Map<String, ResponseDicitemDTO> getDictItemCodeMap() {
        Map<String, ResponseDicitemDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.DICT_ITEM_CODE_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.DICT_ITEM_CODE_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseDicitemDTO.class));
            });
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String,com.ybzt.micro.api.dto.admin.sys.dict.ResponseDicitemDTO> :
     * @Description //获取数据字典集合（key=id）
     * @Author yangll
     * @Date 2019/6/27 15:35
     **/
    public Map<String, ResponseDicitemDTO> getDictItemIdMap() {
        Map<String, ResponseDicitemDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.DICT_ITEM_ID_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.DICT_ITEM_ID_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseDicitemDTO.class));
            });
        }
        return map;
    }

    /**
     * @param categoryDTOS : 商品类型集合
     * @return void :
     * @Description //缓存商品集合
     * @Author yangll
     * @Date 2019/6/26 14:50
     **/
    public void saveGoodsCategorys(List<ResponseCategoryDTO> categoryDTOS) {
        if (CollectionUtil.isNotEmpty(categoryDTOS)) {
            categoryDTOS.forEach(r -> {
                saveGoodsCategory(r);
            });
        }
    }

    /**
     * @param categoryDTO : 商品类型
     * @return void :
     * @Description //缓存商品
     * @Author yangll
     * @Date 2019/6/26 14:50
     **/
    public void saveGoodsCategory(ResponseCategoryDTO categoryDTO) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY, categoryDTO.getCode(), JSONObject.toJSONString(categoryDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.GOODS_CATEGORY_ID_MAP_KEY, categoryDTO.getId().toString(), JSONObject.toJSONString(categoryDTO));
    }

    /**
     * @param code :商品类型编码
     * @return void :
     * @Description //根据编码删除商品类型缓存
     * @Author yangll
     * @Date 2019/6/26 14:57
     **/
    public void deleteGoodsCategoryByCode(String code) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY, code);
    }

    /**
     * @param id :商品类型id
     * @return void :
     * @Description //根据id删除商品类型缓存
     * @Author yangll
     * @Date 2019/6/26 14:57
     **/
    public void deleteGoodsCategoryById(String id) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.GOODS_CATEGORY_ID_MAP_KEY, id);
    }

    /**
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.goods.category.ResponseCategoryDTO> :
     * @Description //获取商品类型集合（key=id）
     * @Author yangll
     * @Date 2019/6/26 15:03
     **/
    public Map<String, ResponseCategoryDTO> getGoodsCategoryIdMap() {
        Map<String, ResponseCategoryDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.GOODS_CATEGORY_ID_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.GOODS_CATEGORY_ID_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseCategoryDTO.class));
            });
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.goods.category.ResponseCategoryDTO> :
     * @Description //获取商品类型集合（key=code）
     * @Author yangll
     * @Date 2019/6/26 15:03
     **/
    public Map<String, ResponseCategoryDTO> getGoodsCategoryCodeMap() {
        Map<String, ResponseCategoryDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseCategoryDTO.class));
            });
        }
        return map;
    }

    /**
     * @param labelDTOS :商品标签集合
     * @return void :
     * @Description //缓存商品标签集合
     * @Author yangll
     * @Date 2019/6/26 14:51
     **/
    public void saveGoodsLabels(List<ResponseLabelDTO> labelDTOS) {
        if (CollectionUtil.isNotEmpty(labelDTOS)) {
            labelDTOS.forEach(r -> {
                saveGoodsLabel(r);
            });
        }
    }

    /**
     * @param labelDTO :商品标签
     * @return void :
     * @Description //缓存商品标签
     * @Author yangll
     * @Date 2019/6/26 14:51
     **/
    public void saveGoodsLabel(ResponseLabelDTO labelDTO) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.GOODS_LABEL_CODE_MAP_KEY, labelDTO.getCode(), JSONObject.toJSONString(labelDTO));
        stringRedisTemplate.opsForHash().put(RedisKeyConst.GOODS_LABEL_ID_MAP_KEY, labelDTO.getId().toString(), JSONObject.toJSONString(labelDTO));
    }

    /**
     * @param code :商品标签编码
     * @return void :
     * @Description //根据编码删除商品标签缓存
     * @Author yangll
     * @Date 2019/6/26 14:57
     **/
    public void deleteGoodsLabelByCode(String code) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.GOODS_LABEL_CODE_MAP_KEY, code);
    }

    /**
     * @param id :商品标签id
     * @return void :
     * @Description //根据id删除商品标签缓存
     * @Author yangll
     * @Date 2019-9-10 11:53:29
     **/
    public void deleteGoodsLabelById(String id) {
        stringRedisTemplate.opsForHash().delete(RedisKeyConst.GOODS_LABEL_ID_MAP_KEY, id);
    }

    /**
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.goods.label.ResponseLabelDTO> :
     * @Description //获取标签集合（key=id）
     * @Author yangll
     * @Date 2019-9-10 11:53:18
     **/
    public Map<String, ResponseLabelDTO> getGoodsLabelIdMap() {
        Map<String, ResponseLabelDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.GOODS_LABEL_ID_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.GOODS_LABEL_ID_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseLabelDTO.class));
            });
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String, com.ybzt.micro.api.dto.admin.goods.label.ResponseLabelDTO> :
     * @Description //获取标签集合（key=code）
     * @Author yangll
     * @Date 2019-9-10 11:52:58
     **/
    public Map<String, ResponseLabelDTO> getGoodsLabelCodeMap() {
        Map<String, ResponseLabelDTO> map = new HashMap<>();
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisKeyConst.GOODS_LABEL_CODE_MAP_KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(k -> {
                String value = stringRedisTemplate.opsForHash().get(RedisKeyConst.GOODS_LABEL_CODE_MAP_KEY, k.toString()).toString();
                map.put(k.toString(), JSONObject.toJavaObject(JSONObject.parseObject(value), ResponseLabelDTO.class));
            });
        }
        return map;
    }

    /**
     * @param msgCode  : 消息模板代码
     * @param template : 消息模板
     * @return void :
     * @Description //缓存消息模板
     * @Author yangll
     * @Date 2019-9-10 11:52:41
     **/
    public void saveMsgTemplate(String msgCode, String template) {
        stringRedisTemplate.opsForHash().put(RedisKeyConst.MSG_TEMPLATE_MAP_KEY, msgCode, template);
    }

    public void deleteMsgTemplate() {
        Set<Object> templates = stringRedisTemplate.opsForHash().keys(RedisKeyConst.MSG_TEMPLATE_MAP_KEY);
        if (CollectionUtil.isNotEmpty(templates)) {
            templates.forEach(k -> {
                stringRedisTemplate.opsForHash().delete(RedisKeyConst.MSG_TEMPLATE_MAP_KEY, k);
            });
        }
    }

    /**
     * @param msgCode    :模板编码
     * @param defaultMsg :默认消息
     * @param params     :占位内容
     * @return java.lang.String :
     * @Description //从缓存中获取消息模板
     * @Author yangll
     * @Date 2019-9-10 11:52:28
     **/
    public String msgTemplate(String msgCode, String defaultMsg, Object... params) {
        Object template = stringRedisTemplate.opsForHash().get(RedisKeyConst.MSG_TEMPLATE_MAP_KEY, msgCode);
        return null == template ? MessageFormat.format(defaultMsg, params) : MessageFormat.format(template.toString(), params);
    }

    /**
     * @param enumClass : 状态码枚举类
     * @param params    : 占位内容
     * @return java.lang.String :
     * @Description //从缓存中获取消息模板
     * @Author yangll
     * @Date 2019-9-10 11:52:18
     **/
    public String msgTemplate(CodeEnumClass enumClass, Object... params) {
        return msgTemplate(((Enum) enumClass).name(), enumClass.getEnumMsg(), params);
    }

    /**
     * @param mobile         :手机号
     * @param moudleEnum     :验证码类型
     * @param captcha        :验证码
     * @param captchaExpTime :失效时间(分钟)
     * @return void :
     * @Description //缓存手机验证码
     * @Author yangll
     * @Date 2019-9-10 11:52:01
     **/
    public void saveMobileCaptcha(String mobile, CaptchaMoudleEnum moudleEnum, String captcha, int captchaExpTime) {
        stringRedisTemplate.opsForValue().set(RedisKeyConst.getMobileCaptchaKey(mobile, moudleEnum), captcha, captchaExpTime, TimeUnit.MINUTES);
    }

    /**
     * @param mobile     :手机号
     * @param moudleEnum :验证码类型
     * @return java.lang.String :
     * @Description //获取验证码
     * @Author yangll
     * @Date 2019-9-10 11:51:50
     **/
    public String getMobileCaptcha(String mobile, CaptchaMoudleEnum moudleEnum) {
        return stringRedisTemplate.opsForValue().get(RedisKeyConst.getMobileCaptchaKey(mobile, moudleEnum));
    }

    /**
     * @param mobile     :手机号
     * @param moudleEnum :验证码类型
     * @return java.lang.String :
     * @Description //获取验证码过期时间（秒数）
     * @Author yangll
     * @Date 2019-9-10 11:51:28
     **/
    public Long getMobileCaptchaExpire(String mobile, CaptchaMoudleEnum moudleEnum) {
        return stringRedisTemplate.getExpire(RedisKeyConst.getMobileCaptchaKey(mobile, moudleEnum), TimeUnit.SECONDS);
    }

    /**
     * @param key   : 缓存key
     * @param value :缓存value
     * @return void :
     * @Description //设置缓存
     * @Author yangll
     * @Date 2019-9-10 11:51:18
     **/
    public void setStringValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * @param key     : 缓存key
     * @param value   :缓存value
     * @param timeout :缓存过期时间（秒）
     * @return void :
     * @Description //设置缓存
     * @Author yangll
     * @Date 2019-9-10 11:50:48
     **/
    public void setStringValue(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * @param key :缓存key
     * @return java.lang.String :
     * @Description //获取缓存
     * @Author yangll
     * @Date 2019-9-10 11:50:58
     **/
    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * @param key :缓存key
     * @return java.lang.Long :
     * @Description //计数
     * @Author yangll
     * @Date 2019-9-10 11:51:07
     **/
    public Long increment(String key) {
        return stringRedisTemplate.boundValueOps(key).increment(1);
    }

}
