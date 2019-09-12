package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAreaStreet;
import com.ydhl.micro.base.entity.SysAreaStreetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaStreetMapper {
    long countByExample(SysAreaStreetExample example);

    int deleteByExample(SysAreaStreetExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAreaStreet record);

    int insertSelective(SysAreaStreet record);

    List<SysAreaStreet> selectByExample(SysAreaStreetExample example);

    SysAreaStreet selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") SysAreaStreet record, @Param("example") SysAreaStreetExample example);

    int updateByExample(@Param("record") SysAreaStreet record, @Param("example") SysAreaStreetExample example);

    int updateByPrimaryKeySelective(SysAreaStreet record);

    int updateByPrimaryKey(SysAreaStreet record);
}
