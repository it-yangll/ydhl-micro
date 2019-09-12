package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAreaDistrict;
import com.ydhl.micro.base.entity.SysAreaDistrictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaDistrictMapper {
    long countByExample(SysAreaDistrictExample example);

    int deleteByExample(SysAreaDistrictExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAreaDistrict record);

    int insertSelective(SysAreaDistrict record);

    List<SysAreaDistrict> selectByExample(SysAreaDistrictExample example);

    SysAreaDistrict selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") SysAreaDistrict record, @Param("example") SysAreaDistrictExample example);

    int updateByExample(@Param("record") SysAreaDistrict record, @Param("example") SysAreaDistrictExample example);

    int updateByPrimaryKeySelective(SysAreaDistrict record);

    int updateByPrimaryKey(SysAreaDistrict record);
}
