package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAreaCity;
import com.ydhl.micro.base.entity.SysAreaCityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaCityMapper {
    long countByExample(SysAreaCityExample example);

    int deleteByExample(SysAreaCityExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAreaCity record);

    int insertSelective(SysAreaCity record);

    List<SysAreaCity> selectByExample(SysAreaCityExample example);

    SysAreaCity selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") SysAreaCity record, @Param("example") SysAreaCityExample example);

    int updateByExample(@Param("record") SysAreaCity record, @Param("example") SysAreaCityExample example);

    int updateByPrimaryKeySelective(SysAreaCity record);

    int updateByPrimaryKey(SysAreaCity record);
}
