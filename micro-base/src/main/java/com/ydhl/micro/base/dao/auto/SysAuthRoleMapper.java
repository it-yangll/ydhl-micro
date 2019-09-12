package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAuthRole;
import com.ydhl.micro.base.entity.SysAuthRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAuthRoleMapper {
    long countByExample(SysAuthRoleExample example);

    int deleteByExample(SysAuthRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAuthRole record);

    int insertSelective(SysAuthRole record);

    List<SysAuthRole> selectByExample(SysAuthRoleExample example);

    SysAuthRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAuthRole record, @Param("example") SysAuthRoleExample example);

    int updateByExample(@Param("record") SysAuthRole record, @Param("example") SysAuthRoleExample example);

    int updateByPrimaryKeySelective(SysAuthRole record);

    int updateByPrimaryKey(SysAuthRole record);
}
