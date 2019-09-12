package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ResourceTreeDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ResponseResourceDTO;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.ResourceType;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysResourceMapper;
import com.ydhl.micro.base.dao.auto.SysRoleResourceMapper;
import com.ydhl.micro.base.entity.SysResource;
import com.ydhl.micro.base.entity.SysResourceExample;
import com.ydhl.micro.base.entity.SysRoleResourceExample;
import com.ydhl.micro.base.service.SysResourceService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper mapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public List<ResponseResourceDTO> findAll() {
        List<SysResource> resources = mapper.selectByExample(null);
        List<ResponseResourceDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(resources)) {
            resources.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    @Override
    public ResponseResourceDTO findById(Long id) {
        SysResource resource = mapper.selectByPrimaryKey(id);
        return trans2DTO(resource);
    }

    private ResponseResourceDTO trans2DTO(SysResource resource) {
        ResponseResourceDTO responseResourceDTO = new ResponseResourceDTO();
        BeanUtil.copyProperties(resource, responseResourceDTO);
        responseResourceDTO.setAvailableName(resource.getAvailable() ? "正常" : "禁用");
        responseResourceDTO.setResourceTypeName(ResourceType.valueOf(resource.getResourceType()).getShowText());

        return responseResourceDTO;
    }

    @Override
    public List<ResourceTreeDTO> resourceTree() {
        SysResourceExample exp = new SysResourceExample();
        exp.setOrderByClause("sort asc");
        return buildResourceTree(mapper.selectByExample(exp));
    }

    private List<ResourceTreeDTO> buildResourceTree(List<SysResource> resources) {
        List<ResourceTreeDTO> result = new ArrayList<>();

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
                ResourceTreeDTO rootTree = new ResourceTreeDTO();
                BeanUtil.copyProperties(rootResource, rootTree);
                rootTree.setAvailableName(rootResource.getAvailable() ? "正常" : "禁用");
                rootTree.setResourceTypeName(ResourceType.valueOf(rootResource.getResourceType()).getShowText());

                renderChildren(getChildResources(rootResource.getId(), resources), resources, rootTree);
                result.add(rootTree);
            }
        }
        return result;
    }

    /** 根据resourceId从resources集合中获取子资源列表 */
    private List<SysResource> getChildResources(Long resourceId, List<SysResource> resources) {
        List<SysResource> child = new ArrayList<>();
        for (SysResource resource : resources) {
            // 如果当前资源的父ID等于传过来的resourceId，加入到子资源列表中
            if (resource.getParentId().intValue() == resourceId.intValue()) {
                child.add(resource);
            }
        }
        return child;
    }

    private void renderChildren(List<SysResource> childResources, List<SysResource> resourceList, ResourceTreeDTO parentResource) {

        List<ResourceTreeDTO> childTree = new ArrayList<ResourceTreeDTO>();
        // 遍历子菜单集合
        for (SysResource resource : childResources) {
            List<SysResource> childs = getChildResources(resource.getId(), resourceList);
            ResourceTreeDTO menu = new ResourceTreeDTO();
            BeanUtil.copyProperties(resource, menu);
            menu.setAvailableName(resource.getAvailable() ? "正常" : "禁用");
            menu.setResourceTypeName(ResourceType.valueOf(resource.getResourceType()).getShowText());

            if (CollectionUtils.isNotEmpty(childs)) {
                // 递归生成子菜单
                renderChildren(childs, resourceList, menu);
            }
            childTree.add(menu);
        }
        parentResource.setChildren(childTree);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateResourceDTO dto) {
        SysResource record = new SysResource();
        BeanUtil.copyProperties(dto, record);
        record.setResourceType(ResourceType.valueOf(dto.getResourceType()).name());

        String parentPath = "";
        record.setCreateId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setCreateTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setModifyTime(new Date());
        record.setAvailable(true);

        if (dto.getParentId() == null || dto.getParentId().intValue() == 0) {
            record.setParentId(0L);
            parentPath = "0";
        } else {
            SysResource parentResource = mapper.selectByPrimaryKey(dto.getParentId());
            parentPath = parentResource.getParentPath().concat("/").concat(parentResource.getId().toString());
        }

        record.setParentPath(parentPath);
        mapper.insertSelective(record);
        cacheHelper.saveResource(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyResourceDTO dto) {
        SysResource record = mapper.selectByPrimaryKey(dto.getId());
        BeanUtil.copyProperties(dto, record);

        record.setModifyTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setResourceType(ResourceType.valueOf(dto.getResourceType()).name());
        mapper.updateByPrimaryKeySelective(record);
        cacheHelper.saveResource(trans2DTO(record));
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        idList.forEach(id -> {
            SysResourceExample exp = new SysResourceExample();
            exp.createCriteria().andParentIdEqualTo(id);
            if (CollectionUtil.isNotEmpty(mapper.selectByExample(exp))) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_RESOURCE_CHILD, cacheHelper.msgTemplate(AdminCodeEnum.ERR_RESOURCE_CHILD));
            }
            mapper.deleteByPrimaryKey(id);

            SysRoleResourceExample delExp = new SysRoleResourceExample();
            delExp.createCriteria().andResourceIdEqualTo(id);
            roleResourceMapper.deleteByExample(delExp);

            cacheHelper.deleteResource(id);
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void frozen(List<Long> idList) {
        idList.forEach(id -> {
            SysResource record = mapper.selectByPrimaryKey(id);
            record.setAvailable(false);
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveResource(trans2DTO(record));
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> idList) {
        idList.forEach(id -> {
            SysResource record = mapper.selectByPrimaryKey(id);
            record.setAvailable(true);
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveResource(trans2DTO(record));
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }

}
