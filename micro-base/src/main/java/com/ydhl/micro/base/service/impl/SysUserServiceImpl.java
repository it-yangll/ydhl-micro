package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.user.*;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.AuthType;
import com.ydhl.micro.api.enumcode.consts.State;
import com.ydhl.micro.api.enumcode.consts.UserType;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysAuthRoleMapper;
import com.ydhl.micro.base.dao.auto.SysDeptMapper;
import com.ydhl.micro.base.dao.auto.SysRoleMapper;
import com.ydhl.micro.base.dao.auto.SysUserMapper;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.base.service.SysUserService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.CommonUtil;
import com.ydhl.micro.core.util.PasswdUtil;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:37
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public ResponseUserDTO findById(Long id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        return trans2DTO(user);
    }

    @Override
    public List<SysUser> searchByMobile(String mobile) {
        SysUserExample exp = new SysUserExample();
        SysUserExample.Criteria cri = exp.createCriteria();
        cri.andStateNotEqualTo(State.DELETED.name()).andMobileNotEqualTo(State.FROZEN.name()).andMobileEqualTo(mobile);
        List<SysUser> sysUserList = userMapper.selectByExample(exp);
        if(CollectionUtil.isNotEmpty(sysUserList)){
            return sysUserList;
        }
        return null;
    }

    private ResponseUserDTO trans2DTO(SysUser user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        BeanUtil.copyProperties(user, responseUserDTO);
        if (user.getDeptId() != null) {
            responseUserDTO.setDeptName(cacheHelper.getDept(user.getDeptId()).getName());
        }
        if (user.getPostId() != null) {
            responseUserDTO.setPostName(cacheHelper.getDictItemById(user.getPostId()).getItemValue());
        }
        responseUserDTO.setStateName(State.valueOf(user.getState()).getShowText());
        responseUserDTO.setPresetName(user.getPreset() ? "是" : "否");
        responseUserDTO.setUserTypeName(UserType.valueOf(user.getUserType()).getShowText());

        /** 是否是部门管理员 */
        if (user.getDeptId() != null) {
            SysDept dept = deptMapper.selectByPrimaryKey(user.getDeptId());
            if (dept != null && dept.getSuperId() != null && dept.getSuperId().intValue() == user.getId().intValue()) {
                responseUserDTO.setDeptSuper(true);
            }
        }

        SysAuthRoleExample exp = new SysAuthRoleExample();
        exp.createCriteria().andAuthTypeEqualTo(AuthType.USER.name()).andAuthIdEqualTo(user.getId());
        List<SysAuthRole> authRoles = authRoleMapper.selectByExample(exp);
        if (CollectionUtil.isNotEmpty(authRoles)) {
            List<Long> roleIds = new ArrayList<>();
            authRoles.forEach(r -> {
                roleIds.add(r.getRoleId());
            });
            SysRoleExample exp1 = new SysRoleExample();
            exp1.createCriteria().andIdIn(roleIds);
            List<SysRole> roles = roleMapper.selectByExample(exp1);
            if (CollectionUtil.isNotEmpty(roles)) {
                StringBuilder rIds = new StringBuilder();
                StringBuilder roleNames = new StringBuilder();
                roles.forEach(r -> {
                    rIds.append(r.getId()).append(",");
                    roleNames.append(r.getName()).append(",");
                });
                responseUserDTO.setRoleIds(StringUtils.left(rIds.toString(), rIds.toString().length() - 1));
                responseUserDTO.setRoleNames(StringUtils.left(roleNames.toString(), roleNames.toString().length() - 1));
            }
        }
        return responseUserDTO;
    }

    @Override
    public PageInfo<ResponseUserDTO> pageSearch(SearchUserDTO searchDTO) {
        log.info("pageSearch:{}", searchDTO);

        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        Page<SysUser> users = (Page<SysUser>) userMapper.selectByExample(createQuery(searchDTO));

        List<ResponseUserDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(users)) {
            users.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }

        PageInfo<ResponseUserDTO> result = new PageInfo(users);
        result.setList(dtos);
        return result;
    }

    @Override
    public List<ResponseUserDTO> search(SearchUserDTO searchDTO) {
        log.info("search:{}", searchDTO);
        List<SysUser> users = userMapper.selectByExample(createQuery(searchDTO));
        List<ResponseUserDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(users)) {
            users.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    private SysUserExample createQuery(SearchUserDTO dto) {
        SysUserExample exp = new SysUserExample();
        SysUserExample.Criteria cri = exp.createCriteria();
        cri.andStateNotEqualTo(State.DELETED.name());

        if (StringUtils.isNotBlank(dto.getName())) {
            cri.andNameLike("%" + dto.getName() + "%");
        }

        if (StringUtils.isNotBlank(dto.getUserName())) {
            cri.andUserNameLike("%" + dto.getUserName() + "%");
        }

        if (StringUtils.isNotBlank(dto.getMobile())) {
            cri.andMobileLike("%" + dto.getMobile() + "%");
        }

        if (dto.getDeptId() != null) {
            cri.andDeptIdEqualTo(dto.getDeptId());
        }

        if (dto.getPostId() != null) {
            cri.andPostIdEqualTo(dto.getPostId());
        }

        /** 排序 */
        String order = "";
        if (StringUtils.isNotBlank(dto.getSortBy())) {
            order = order.concat(dto.getSortBy());

            if (StringUtils.isNotBlank(dto.getOrderBy())) {
                order = order.concat(" ").concat(dto.getOrderBy());
            }
            exp.setOrderByClause(order);
        }
        return exp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserDTO dto) {
        SysUserExample exp = new SysUserExample();
        exp.createCriteria().andUserNameEqualTo(dto.getUserName());
        List<SysUser> sysUsers = userMapper.selectByExample(exp);
        if (CollectionUtil.isNotEmpty(sysUsers)) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_USER_REPEAT, cacheHelper.msgTemplate(AdminCodeEnum.ERR_USER_REPEAT));
        }

        SysUser record = new SysUser();
        BeanUtil.copyProperties(dto, record);
        record.setState(State.NORMAL.name());
        record.setCreateId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setCreateTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setModifyTime(new Date());
        record.setSalt(PasswdUtil.generateSalt());
        record.setPassword(PasswdUtil.generatePasswd(PasswdUtil.getResetPasswd(dto.getMobile()), record.getSalt()));
        record.setPreset(false);
        record.setUserType(UserType.EMPLOYEE.name());
        userMapper.insertSelective(record);

        if (dto.getDeptId() != null && dto.getDeptSuper()) {
            SysDept dept = deptMapper.selectByPrimaryKey(dto.getDeptId());
            dept.setSuperId(userMapper.selectByExample(exp).get(0).getId());
            deptMapper.updateByPrimaryKeySelective(dept);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyUserDTO dto) {
        SysUser record = userMapper.selectByPrimaryKey(dto.getId());
        if (StringUtils.equalsIgnoreCase(record.getUserType(), UserType.ADMIN.name())) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
        }
        if (record.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        if (dto.getDeptId() == null && record.getDeptId() != null) {
            SysDept dept = deptMapper.selectByPrimaryKey(record.getDeptId());
            dept.setSuperId(null);
            deptMapper.updateByPrimaryKey(dept);
        }
        if (dto.getDeptId() != null && record.getDeptId() != null && dto.getDeptId().intValue() != record.getDeptId().intValue()) {
            SysDept dept = deptMapper.selectByPrimaryKey(record.getDeptId());
            dept.setSuperId(null);
            deptMapper.updateByPrimaryKey(dept);
        }
        BeanUtil.copyProperties(dto, record);
        record.setModifyTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        userMapper.updateByPrimaryKeySelective(record);

        if (dto.getDeptId() != null) {
            SysDept dept = deptMapper.selectByPrimaryKey(dto.getDeptId());
            if (dto.getDeptSuper()) {
                dept.setSuperId(dto.getId());
            } else {
                dept.setSuperId(null);
            }
            deptMapper.updateByPrimaryKey(dept);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        idList.forEach(id -> {
            SysUser record = userMapper.selectByPrimaryKey(id);
            if (StringUtils.equalsIgnoreCase(record.getUserType(), UserType.ADMIN.name())) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
            }
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setState(State.DELETED.name());
            record.setUserName(CommonUtil.appendDeleteUnique(record.getUserName()));
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            userMapper.updateByPrimaryKeySelective(record);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void frozen(List<Long> idList) {
        idList.forEach(id -> {
            SysUser record = userMapper.selectByPrimaryKey(id);
            if (StringUtils.equalsIgnoreCase(record.getUserType(), UserType.ADMIN.name())) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
            }
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setState(State.FROZEN.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            userMapper.updateByPrimaryKeySelective(record);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> idList) {
        idList.forEach(id -> {
            SysUser record = userMapper.selectByPrimaryKey(id);
            if (StringUtils.equalsIgnoreCase(record.getUserType(), UserType.ADMIN.name())) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
            }
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            if (StringUtils.equalsIgnoreCase(record.getState(), State.FROZEN.name())) {
                record.setState(State.NORMAL.name());
                record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
                record.setModifyTime(new Date());
                userMapper.updateByPrimaryKeySelective(record);
            } else {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_FROZEN_STATE_INFO, cacheHelper.msgTemplate(AdminCodeEnum.ERR_FROZEN_STATE_INFO));
            }

        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(List<Long> idList) {
        idList.forEach(id -> {
            SysUser record = userMapper.selectByPrimaryKey(id);
            if (StringUtils.equalsIgnoreCase(record.getUserType(), UserType.ADMIN.name())) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
            }
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setPassword(PasswdUtil.generatePasswd(PasswdUtil.getResetPasswd(record.getMobile()), record.getSalt()));
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            userMapper.updateByPrimaryKeySelective(record);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(AssignRoleDTO dto) {
        SysUser user = userMapper.selectByPrimaryKey(dto.getUserId());
        if (StringUtils.equalsIgnoreCase(user.getUserType(), UserType.ADMIN.name())) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_DEL_ADMIN_USER, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DEL_ADMIN_USER));
        }
        if (user.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        SysAuthRoleExample delExp = new SysAuthRoleExample();
        delExp.createCriteria().andAuthTypeEqualTo(AuthType.USER.name()).andAuthIdEqualTo(dto.getUserId());
        authRoleMapper.deleteByExample(delExp);
        dto.getRoleIds().forEach(r -> {
            SysAuthRole record = new SysAuthRole();
            record.setAuthId(dto.getUserId());
            record.setAuthType(AuthType.USER.name());
            record.setRoleId(r);
            authRoleMapper.insertSelective(record);
        });

        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission(user.getUserName());
    }

    @Override
    public void updatePwd(UpdatePwdDTO dto) {
        if (!StringUtils.equals(dto.getNewPwd(), dto.getConfirmPwd())) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_UPDATE_PWD, cacheHelper.msgTemplate(AdminCodeEnum.ERR_UPDATE_PWD));
        }
        SysUser user = userMapper.selectByPrimaryKey(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        if (!StringUtils.equals(user.getPassword(), PasswdUtil.generatePasswd(dto.getOldPwd(), user.getSalt()))) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_OLD_PWD, cacheHelper.msgTemplate(AdminCodeEnum.ERR_OLD_PWD));
        }

        user.setPassword(PasswdUtil.generatePasswd(dto.getNewPwd(), user.getSalt()));
        user.setModifyTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
