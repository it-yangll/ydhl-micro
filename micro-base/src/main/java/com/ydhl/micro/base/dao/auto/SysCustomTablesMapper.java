package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysCustomTables;
import com.ydhl.micro.base.entity.SysCustomTablesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCustomTablesMapper {
    long countByExample(SysCustomTablesExample example);

    int deleteByExample(SysCustomTablesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCustomTables record);

    int insertSelective(SysCustomTables record);

    List<SysCustomTables> selectByExample(SysCustomTablesExample example);

    SysCustomTables selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCustomTables record, @Param("example") SysCustomTablesExample example);

    int updateByExample(@Param("record") SysCustomTables record, @Param("example") SysCustomTablesExample example);

    int updateByPrimaryKeySelective(SysCustomTables record);

    int updateByPrimaryKey(SysCustomTables record);
}
