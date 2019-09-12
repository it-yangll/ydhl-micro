package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.post.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.post.ResponsePostDTO;
import com.ydhl.micro.api.dto.admin.sys.post.SearchPostDTO;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.AuthType;
import com.ydhl.micro.api.enumcode.consts.State;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysAuthRoleMapper;
import com.ydhl.micro.base.dao.auto.SysDicitemMapper;
import com.ydhl.micro.base.dao.auto.SysRoleMapper;
import com.ydhl.micro.base.service.SysPostService;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.core.consts.DictCodeConst;
import com.ydhl.micro.core.util.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysPostServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 16:55
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysPostServiceImpl implements SysPostService {

    @Autowired
    private SysDicitemMapper mapper;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private CacheHelper cacheHelper;


    private ResponsePostDTO trans2DTO(SysDicitem item) {
        ResponsePostDTO responsePostDTO = new ResponsePostDTO();
        BeanUtil.copyProperties(item, responsePostDTO);
        responsePostDTO.setPresetName(item.getPreset() ? "是" : "否");
        responsePostDTO.setStateName(State.valueOf(item.getState()).getShowText());

        SysAuthRoleExample exp = new SysAuthRoleExample();
        exp.createCriteria().andAuthTypeEqualTo(AuthType.POST.name()).andAuthIdEqualTo(item.getId());
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
                responsePostDTO.setRoleIds(StringUtils.left(rIds.toString(), rIds.toString().length() - 1));
                responsePostDTO.setRoleNames(StringUtils.left(roleNames.toString(), roleNames.toString().length() - 1));
            }
        }
        return responsePostDTO;
    }

    @Override
    public PageInfo<ResponsePostDTO> pageSearch(SearchPostDTO searchDTO) {
        log.info("pageSearch:{}", searchDTO);

        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        Page<SysDicitem> dicitems = (Page<SysDicitem>) mapper.selectByExample(createQuery(searchDTO));

        List<ResponsePostDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(dicitems)) {
            dicitems.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }

        PageInfo<ResponsePostDTO> result = new PageInfo(dicitems);
        result.setList(dtos);
        return result;
    }

    @Override
    public List<ResponsePostDTO> search(SearchPostDTO searchDTO) {
        log.info("search:{}", searchDTO);
        List<SysDicitem> dicitems = mapper.selectByExample(createQuery(searchDTO));
        List<ResponsePostDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(dicitems)) {
            dicitems.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    private SysDicitemExample createQuery(SearchPostDTO dto) {
        SysDicitemExample exp = new SysDicitemExample();
        SysDicitemExample.Criteria cri = exp.createCriteria();
        cri.andTypeCodeEqualTo(DictCodeConst.post).andStateNotEqualTo(State.DELETED.name());

        if (StringUtils.isNotBlank(dto.getName())) {
            cri.andItemValueLike("%" + dto.getName() + "%");
        }

        if (StringUtils.isNotBlank(dto.getCode())) {
            cri.andItemCodeLike("%" + dto.getCode() + "%");
        }

        /** 排序 */
        String order = "";
        if (StringUtils.isNotBlank(dto.getSortBy())) {
            order = order.concat(dto.getSortBy());

            if (StringUtils.isNotBlank(dto.getOrderBy())) {
                order = order.concat(" ").concat(dto.getOrderBy());
            }
        }
        if (StringUtils.isNotBlank(order)) {
            exp.setOrderByClause(order);
        } else {
            exp.setOrderByClause("sort asc");
        }
        return exp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(AssignRoleDTO dto) {
        SysDicitem dicitem = mapper.selectByPrimaryKey(dto.getPostId());
        if (dicitem.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        SysAuthRoleExample delExp = new SysAuthRoleExample();
        delExp.createCriteria().andAuthTypeEqualTo(AuthType.POST.name()).andAuthIdEqualTo(dto.getPostId());
        authRoleMapper.deleteByExample(delExp);
        dto.getRoleIds().forEach(r -> {
            SysAuthRole record = new SysAuthRole();
            record.setAuthId(dto.getPostId());
            record.setAuthType(AuthType.POST.name());
            record.setRoleId(r);
            authRoleMapper.insertSelective(record);
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }
}
