package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.role.*;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysAuthRoleMapper;
import com.ydhl.micro.base.dao.auto.SysRoleMapper;
import com.ydhl.micro.base.dao.auto.SysRoleResourceMapper;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.base.service.SysRoleService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.TokenUtil;
import com.ydhl.micro.base.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysRoleServiceImpl
 * @Description 角色管理
 * @Author Ly
 * @Date 2019/4/25 13:37
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public SysRole findById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }


    private ResponseRoleDTO trans2DTO(SysRole role) {
        ResponseRoleDTO responseRoleDTO = new ResponseRoleDTO();
        BeanUtil.copyProperties(role, responseRoleDTO);

        responseRoleDTO.setStateName(role.getAvailable() ? "正常" : "禁用");
        responseRoleDTO.setPresetName(role.getPreset() ? "是" : "否");

        return responseRoleDTO;
    }

    @Override
    public PageInfo<ResponseRoleDTO> pageSearch(SearchRoleDTO searchDTO) {
        log.info("pageSearch:{}", searchDTO);

        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        Page<SysRole> roles = (Page<SysRole>) roleMapper.selectByExample(createQuery(searchDTO));

        List<ResponseRoleDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            roles.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }

        PageInfo<ResponseRoleDTO> result = new PageInfo(roles);
        result.setList(dtos);
        return result;
    }

    @Override
    public List<ResponseRoleDTO> search(SearchRoleDTO searchDTO) {
        log.info("search:{}", searchDTO);
        List<SysRole> roles = roleMapper.selectByExample(createQuery(searchDTO));
        List<ResponseRoleDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            roles.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    private SysRoleExample createQuery(SearchRoleDTO dto) {
        SysRoleExample exp = new SysRoleExample();
        SysRoleExample.Criteria cri = exp.createCriteria();

        if (StringUtils.isNotBlank(dto.getName())) {
            cri.andNameLike("%" + dto.getName() + "%");
        }

        if (StringUtils.isNotBlank(dto.getRole())) {
            cri.andRoleLike("%" + dto.getRole() + "%");
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
    public void create(CreateRoleDTO dto) {
        SysRoleExample exp = new SysRoleExample();
        exp.createCriteria().andRoleEqualTo(dto.getRole());
        if (CollectionUtil.isNotEmpty(roleMapper.selectByExample(exp))) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_ROLE_REPEAT, cacheHelper.msgTemplate(AdminCodeEnum.ERR_ROLE_REPEAT));
        }
        SysRole record = new SysRole();
        BeanUtil.copyProperties(dto, record);

        record.setCreateId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setCreateTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setModifyTime(new Date());

        record.setAvailable(true);
        record.setPreset(false);

        roleMapper.insertSelective(record);
        cacheHelper.saveRole(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyRoleDTO dto) {
        SysRole record = roleMapper.selectByPrimaryKey(dto.getId());
        if (record.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        BeanUtil.copyProperties(dto, record);
        record.setModifyTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        roleMapper.updateByPrimaryKeySelective(record);
        cacheHelper.saveRole(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        idList.forEach(id -> {
            SysRole record = roleMapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            roleMapper.deleteByPrimaryKey(id);
            SysRoleResourceExample delExp = new SysRoleResourceExample();
            delExp.createCriteria().andRoleIdEqualTo(id);
            roleResourceMapper.deleteByExample(delExp);

            SysAuthRoleExample delExp1 = new SysAuthRoleExample();
            delExp1.createCriteria().andRoleIdEqualTo(id);
            authRoleMapper.deleteByExample(delExp1);
            cacheHelper.deleteRole(id);
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void frozen(List<Long> idList) {
        idList.forEach(id -> {
            SysRole record = roleMapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setAvailable(false);
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            roleMapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveRole(trans2DTO(record));
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> idList) {
        idList.forEach(id -> {
            SysRole record = roleMapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setAvailable(true);
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            roleMapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveRole(trans2DTO(record));
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignResource(AssignResourceDTO dto) {
        SysRole role = roleMapper.selectByPrimaryKey(dto.getRoleId());
        if (role.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        SysRoleResourceExample delExp = new SysRoleResourceExample();
        delExp.createCriteria().andRoleIdEqualTo(dto.getRoleId());
        roleResourceMapper.deleteByExample(delExp);
        dto.getResourceIds().forEach(r -> {
            SysRoleResource record = new SysRoleResource();
            record.setRoleId(dto.getRoleId());
            record.setResourceId(r);
            roleResourceMapper.insertSelective(record);
        });

        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }
}
