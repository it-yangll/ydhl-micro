package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAreaProvince;
import com.ydhl.micro.base.entity.SysAreaProvinceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaProvinceMapper {
    long countByExample(SysAreaProvinceExample example);

    int deleteByExample(SysAreaProvinceExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAreaProvince record);

    int insertSelective(SysAreaProvince record);

    List<SysAreaProvince> selectByExample(SysAreaProvinceExample example);

    SysAreaProvince selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") SysAreaProvince record, @Param("example") SysAreaProvinceExample example);

    int updateByExample(@Param("record") SysAreaProvince record, @Param("example") SysAreaProvinceExample example);

    int updateByPrimaryKeySelective(SysAreaProvince record);

    int updateByPrimaryKey(SysAreaProvince record);
}
