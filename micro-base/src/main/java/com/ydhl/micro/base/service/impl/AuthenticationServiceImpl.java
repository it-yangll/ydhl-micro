package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.sys.Menu;
import com.ydhl.micro.api.dto.admin.sys.NavTreeDTO;
import com.ydhl.micro.api.dto.admin.sys.login.LoginAdminDTO;
import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.admin.sys.role.RoleResourceTreeDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.enumcode.consts.*;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.*;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.base.service.AuthenticationService;
import com.ydhl.micro.core.security.JwtBody;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.IpUtil;
import com.ydhl.micro.core.util.PasswdUtil;
import com.ydhl.micro.core.util.TokenUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName AuthenticationServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/29 19:38
 * @Version 1.0
 **/
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Value("${token_exp_time}")
    private long expTime;

    @Override
    public ResponseLoginDTO login(LoginAdminDTO dto) {
        SysUserExample exp = new SysUserExample();
        exp.createCriteria().andUserNameEqualTo(dto.getName()).andStateNotEqualTo(State.DELETED.name());

        List<SysUser> users = userMapper.selectByExample(exp);
        if (CollectionUtil.isEmpty(users)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_LOGIN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_LOGIN));
        }else if(StringUtils.equalsIgnoreCase(users.get(0).getState(),State.FROZEN.name())){
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_FROZEN_ACCOUNT, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_FROZEN_ACCOUNT));
        }
        String encryPwd = PasswdUtil.generatePasswd(dto.getPasswd(), users.get(0).getSalt());
        if (!StringUtils.equals(encryPwd, users.get(0).getPassword())) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_LOGIN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_LOGIN));
        }

        ResponseLoginDTO result = new ResponseLoginDTO();
        BeanUtil.copyProperties(users.get(0), result);

        result.setAccessToken(JwtUtil.buildJWT(JwtBody.createJwtBody(UUID.randomUUID().toString(), expTime, IpUtil.getIpAddr(), LoginType.ADMIN, UserType.valueOf(users.get(0).getUserType()), users.get(0).getUserName(), users.get(0).getId(), users.get(0).getName())));

        //token放入redis 供网关Token过滤器使用
        cacheHelper.saveUserToken(users.get(0).getUserName(), LoginType.ADMIN, UserType.valueOf(users.get(0).getUserType()), result.getAccessToken(), expTime);

        result.setPermissions(getUserPermissions(users.get(0)));
        result.setRoles(getRoles(users.get(0)));

        cacheHelper.saveUserPermission(result.getUserName(), result.getRoles(), result.getPermissions());
        return result;
    }

    @Override
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

    @Override
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

    @Override
    public List<NavTreeDTO> navTree() {
        Long userId = JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk();
        SysUser user = userMapper.selectByPrimaryKey(userId);
        if (StringUtils.equalsIgnoreCase(UserType.ADMIN.name(), user.getUserType())) {
            SysResourceExample exp = new SysResourceExample();
            exp.createCriteria().andAvailableEqualTo(true).andResourceTypeNotEqualTo(ResourceType.BUTTON.name());
            exp.setOrderByClause("sort asc");
            return buildNavTree(resourceMapper.selectByExample(exp));
        } else {
            List<Long> userRoleIds = getUserRoleIds(user);
            List<Long> userResourceIds = getResourceByRoles(userRoleIds);
            if (CollectionUtil.isNotEmpty(userResourceIds)) {
                SysResourceExample exp = new SysResourceExample();
                exp.createCriteria().andIdIn(userResourceIds).andAvailableEqualTo(true).andResourceTypeNotEqualTo(ResourceType.BUTTON.name());
                exp.setOrderByClause("sort asc");
                return buildNavTree(resourceMapper.selectByExample(exp));
            }
            return null;
        }
    }

    private List<NavTreeDTO> buildNavTree(List<SysResource> resources) {
        List<NavTreeDTO> result = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(resources)) {
            List<SysResource> root = new ArrayList<>();
            resources.forEach(r -> {
                if (r.getParentId().intValue() == 0) {
                    root.add(r);
                }
            });

            // 先生成根菜单
            for (SysResource rootResource : root) {
                // 生成根菜单
                NavTreeDTO rootMenu = new NavTreeDTO();
                Menu menuInfo = new Menu();
                BeanUtil.copyProperties(rootResource, menuInfo);
                rootMenu.setMenu(menuInfo);
                renderChildren(getChildResources(rootResource.getId(), resources), resources, rootMenu);
                result.add(rootMenu);
            }
        }
        return result;
    }

    /** 根据resourceId从resources集合中获取子资源列表 */
    private List<SysResource> getChildResources(Long resourceId, List<SysResource> resources) {
        List<SysResource> childResources = new ArrayList<SysResource>();
        for (SysResource res : resources) {
            // 如果当前资源的父ID等于传过来的resourceId，加入到子资源列表中
            if (res.getParentId().intValue() == resourceId.intValue()) {
                childResources.add(res);
            }
        }
        return childResources;
    }

    private void renderChildren(List<SysResource> childResources, List<SysResource> resourceList, NavTreeDTO parentMenu) {

        List<NavTreeDTO> childTree = new ArrayList<NavTreeDTO>();
        // 遍历子菜单集合
        for (SysResource res : childResources) {
            NavTreeDTO tree = new NavTreeDTO();
            Menu menuInfo = new Menu();
            BeanUtil.copyProperties(res, menuInfo);
            tree.setMenu(menuInfo);
            childTree.add(tree);

            List<SysResource> childs = getChildResources(res.getId(), resourceList);
            if (CollectionUtils.isNotEmpty(childs)) {
                // 递归生成子菜单
                Menu resMenu = new Menu();
                BeanUtil.copyProperties(res, resMenu);
                tree.setMenu(resMenu);
                renderChildren(childs, resourceList, tree);
            }
        }
        parentMenu.setChilds(childTree);
    }

    @Override
    public List<RoleResourceTreeDTO> roleResourceTree(Long roleId) {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<Long> resourceIds = getResourceByRoles(roleIds);

        SysResourceExample exp = new SysResourceExample();
        exp.setOrderByClause("sort asc");
        List<RoleResourceTreeDTO> roleResourceTree = buildRoleResourceTree(resourceIds, resourceMapper.selectByExample(exp));
        return roleResourceTree;
    }

    private List<RoleResourceTreeDTO> buildRoleResourceTree(List<Long> resourceIds, List<SysResource> resources) {
        List<RoleResourceTreeDTO> result = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(resources)) {
            List<SysResource> root = new ArrayList<>();
            resources.forEach(r -> {
                if (r.getParentId().intValue() == 0) {
                    root.add(r);
                }
            });

            // 先生成根菜单
            for (SysResource rootResource : root) {
                // 生成根菜单
                RoleResourceTreeDTO rootMenu = new RoleResourceTreeDTO();
                BeanUtil.copyProperties(rootResource, rootMenu);
                if (CollectionUtil.isNotEmpty(resourceIds) && resourceIds.contains(rootMenu.getId())) {
                    rootMenu.setCheck(true);
                }
                renderRoleResourceChildren(resourceIds, getChildResources(rootResource.getId(), resources), resources, rootMenu);
                result.add(rootMenu);
            }
        }
        return result;
    }

    private void renderRoleResourceChildren(List<Long> resourceIds, List<SysResource> childResources, List<SysResource> resourceList, RoleResourceTreeDTO parentMenu) {

        List<RoleResourceTreeDTO> childTree = new ArrayList<RoleResourceTreeDTO>();
        // 遍历子菜单集合
        for (SysResource res : childResources) {
            List<SysResource> childs = getChildResources(res.getId(), resourceList);
            RoleResourceTreeDTO menu = new RoleResourceTreeDTO();
            BeanUtil.copyProperties(res, menu);
            if (CollectionUtil.isNotEmpty(resourceIds) && resourceIds.contains(menu.getId())) {
                menu.setCheck(true);
            }

            if (CollectionUtils.isNotEmpty(childs)) {
                // 递归生成子菜单
                renderRoleResourceChildren(resourceIds, childs, resourceList, menu);
            }
            childTree.add(menu);
        }
        parentMenu.setChildren(childTree);
    }

}
