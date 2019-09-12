package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysAreaVillage;
import com.ydhl.micro.base.entity.SysAreaVillageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaVillageMapper {
    long countByExample(SysAreaVillageExample example);

    int deleteByExample(SysAreaVillageExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAreaVillage record);

    int insertSelective(SysAreaVillage record);

    List<SysAreaVillage> selectByExample(SysAreaVillageExample example);

    SysAreaVillage selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") SysAreaVillage record, @Param("example") SysAreaVillageExample example);

    int updateByExample(@Param("record") SysAreaVillage record, @Param("example") SysAreaVillageExample example);

    int updateByPrimaryKeySelective(SysAreaVillage record);

    int updateByPrimaryKey(SysAreaVillage record);
}
