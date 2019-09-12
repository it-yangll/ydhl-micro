package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.sys.dept.*;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.AuthType;
import com.ydhl.micro.api.enumcode.consts.State;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysAuthRoleMapper;
import com.ydhl.micro.base.dao.auto.SysDeptMapper;
import com.ydhl.micro.base.dao.auto.SysRoleMapper;
import com.ydhl.micro.base.dao.auto.SysUserMapper;
import com.ydhl.micro.base.entity.*;
import com.ydhl.micro.base.service.SysDeptService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.CommonUtil;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:37
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper mapper;
    @Autowired
    private SysAuthRoleMapper authRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public List<ResponseDeptDTO> findAll() {
        List<SysDept> deptList = mapper.selectByExample(null);
        List<ResponseDeptDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(deptList)) {
            deptList.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    @Override
    public ResponseDeptDTO findById(Long id) {
        SysDept dept = mapper.selectByPrimaryKey(id);
        return trans2DTO(dept);
    }

    private ResponseDeptDTO trans2DTO(SysDept dept) {
        ResponseDeptDTO responseDeptDTO = new ResponseDeptDTO();
        BeanUtil.copyProperties(dept, responseDeptDTO);
        responseDeptDTO.setStateName(State.valueOf(dept.getState()).getShowText());

        if (dept.getSuperId() != null) {
            SysUser user = userMapper.selectByPrimaryKey(dept.getSuperId());
            if (user != null) {
                if (StringUtils.equals(user.getState(), State.DELETED.name())) {
                    responseDeptDTO.setSuperMan(CommonUtil.removeDeleteUnique(user.getUserName()));
                } else {
                    responseDeptDTO.setSuperMan(user.getUserName());
                }
            }
        }

        SysAuthRoleExample exp = new SysAuthRoleExample();
        exp.createCriteria().andAuthTypeEqualTo(AuthType.DEPT.name()).andAuthIdEqualTo(dept.getId());
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
                responseDeptDTO.setRoleIds(StringUtils.left(rIds.toString(), rIds.toString().length() - 1));
                responseDeptDTO.setRoleNames(StringUtils.left(roleNames.toString(), roleNames.toString().length() - 1));
            }
        }
        return responseDeptDTO;
    }

    @Override
    public List<ResponseDeptDTO> deptList() {
        SysDeptExample example = new SysDeptExample();
        example.createCriteria().andStateNotEqualTo(State.DELETED.name());
        List<SysDept> deptList = mapper.selectByExample(example);
        List<ResponseDeptDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(deptList)) {
            deptList.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    @Override
    public List<DeptTreeDTO> deptTree() {
        SysDeptExample exp = new SysDeptExample();
        exp.createCriteria().andStateNotEqualTo(State.DELETED.name());
        exp.setOrderByClause("sort asc");
        return buildDeptTree(mapper.selectByExample(exp));
    }

    private List<DeptTreeDTO> buildDeptTree(List<SysDept> depts) {
        List<DeptTreeDTO> result = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(depts)) {
            List<SysDept> root = new ArrayList<>();
            depts.forEach(r -> {
                if (r.getParentId().intValue() == 0) {
                    root.add(r);
                }
            });

            // 先生成根菜单
            for (SysDept rootDept : root) {
                // 生成根菜单
                DeptTreeDTO rootTree = new DeptTreeDTO();
                BeanUtil.copyProperties(rootDept, rootTree);
                rootTree.setStateName(State.valueOf(rootDept.getState()).getShowText());

                if (rootDept.getSuperId() != null) {
                    SysUser user = userMapper.selectByPrimaryKey(rootDept.getSuperId());
                    if (user != null) {
                        if (StringUtils.equals(user.getState(), State.DELETED.name())) {
                            rootTree.setSuperMan(CommonUtil.removeDeleteUnique(user.getUserName()));
                        } else {
                            rootTree.setSuperMan(user.getUserName());
                        }
                    }
                }

                SysAuthRoleExample exp = new SysAuthRoleExample();
                exp.createCriteria().andAuthTypeEqualTo(AuthType.DEPT.name()).andAuthIdEqualTo(rootDept.getId());
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
                        rootTree.setRoleIds(StringUtils.left(rIds.toString(), rIds.toString().length() - 1));
                        rootTree.setRoleNames(StringUtils.left(roleNames.toString(), roleNames.toString().length() - 1));
                    }
                }

                renderChildren(getChildResources(rootDept.getId(), depts), depts, rootTree);
                result.add(rootTree);
            }
        }
        return result;
    }

    /** 根据deptId从depts集合中获取子资源列表 */
    private List<SysDept> getChildResources(Long deptId, List<SysDept> depts) {
        List<SysDept> childDepts = new ArrayList<SysDept>();
        for (SysDept dept : depts) {
            // 如果当前资源的父ID等于传过来的resourceId，加入到子资源列表中
            if (dept.getParentId().intValue() == deptId.intValue()) {
                childDepts.add(dept);
            }
        }
        return childDepts;
    }

    private void renderChildren(List<SysDept> childDepts, List<SysDept> deptList, DeptTreeDTO parentDept) {

        List<DeptTreeDTO> childTree = new ArrayList<DeptTreeDTO>();
        // 遍历子菜单集合
        for (SysDept dept : childDepts) {
            List<SysDept> childs = getChildResources(dept.getId(), deptList);
            DeptTreeDTO menu = new DeptTreeDTO();
            BeanUtil.copyProperties(dept, menu);
            menu.setStateName(State.valueOf(dept.getState()).getShowText());

            if (dept.getSuperId() != null) {
                SysUser user = userMapper.selectByPrimaryKey(dept.getSuperId());
                if (user != null) {
                    if (StringUtils.equals(user.getState(), State.DELETED.name())) {
                        menu.setSuperMan(CommonUtil.removeDeleteUnique(user.getUserName()));
                    } else {
                        menu.setSuperMan(user.getUserName());
                    }
                }
            }

            SysAuthRoleExample exp = new SysAuthRoleExample();
            exp.createCriteria().andAuthTypeEqualTo(AuthType.DEPT.name()).andAuthIdEqualTo(dept.getId());
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
                    menu.setRoleIds(StringUtils.left(rIds.toString(), rIds.toString().length() - 1));
                    menu.setRoleNames(StringUtils.left(roleNames.toString(), roleNames.toString().length() - 1));
                }
            }

            if (CollectionUtils.isNotEmpty(childs)) {
                // 递归生成子菜单
                renderChildren(childs, deptList, menu);
            }
            childTree.add(menu);
        }
        parentDept.setChildren(childTree);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateDeptDTO dto) {
        SysDept record = new SysDept();
        BeanUtil.copyProperties(dto, record);

        String parentPath = "";
        record.setState(State.NORMAL.name());
        record.setCreateId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setCreateTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setModifyTime(new Date());

        if (dto.getParentId() == null || dto.getParentId().intValue() == 0) {
            record.setParentId(0L);
            parentPath = "0";
        } else {
            SysDept parentDept = mapper.selectByPrimaryKey(dto.getParentId());
            parentPath = parentDept.getParentPath().concat("/").concat(parentDept.getId().toString());
        }

        record.setParentPath(parentPath);
        record.setIdentityCode(UUID.randomUUID().toString().replace("-", ""));

        mapper.insertSelective(record);
        cacheHelper.saveDept(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyDeptDTO dto) {
        SysDept record = mapper.selectByPrimaryKey(dto.getId());
        BeanUtil.copyProperties(dto, record);
        record.setModifyTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        mapper.updateByPrimaryKeySelective(record);
        cacheHelper.saveDept(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            SysDeptExample exp = new SysDeptExample();
            exp.createCriteria().andParentIdEqualTo(id).andStateNotEqualTo(State.DELETED.name());
            if (CollectionUtil.isNotEmpty(mapper.selectByExample(exp))) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_RESOURCE_CHILD, cacheHelper.msgTemplate(AdminCodeEnum.ERR_RESOURCE_CHILD));
            }

            SysDept record = mapper.selectByPrimaryKey(id);
            record.setState(State.DELETED.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDept(trans2DTO(record));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void frozen(List<Long> idList) {
        idList.forEach(id -> {
            SysDept record = mapper.selectByPrimaryKey(id);
            record.setState(State.FROZEN.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDept(trans2DTO(record));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> idList) {
        idList.forEach(id -> {
            SysDept record = mapper.selectByPrimaryKey(id);
            record.setState(State.NORMAL.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDept(trans2DTO(record));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(AssignRoleDTO dto) {
        SysAuthRoleExample delExp = new SysAuthRoleExample();
        delExp.createCriteria().andAuthTypeEqualTo(AuthType.DEPT.name()).andAuthIdEqualTo(dto.getDeptId());
        authRoleMapper.deleteByExample(delExp);
        dto.getRoleIds().forEach(r -> {
            SysAuthRole record = new SysAuthRole();
            record.setAuthId(dto.getDeptId());
            record.setAuthType(AuthType.DEPT.name());
            record.setRoleId(r);
            authRoleMapper.insertSelective(record);
        });
        //清空用户权限缓存，使shiro从数据库获取
        cacheHelper.deleteUserPermission();
    }


}
