package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysDicitem;
import com.ydhl.micro.base.entity.SysDicitemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDicitemMapper {
    long countByExample(SysDicitemExample example);

    int deleteByExample(SysDicitemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDicitem record);

    int insertSelective(SysDicitem record);

    List<SysDicitem> selectByExample(SysDicitemExample example);

    SysDicitem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysDicitem record, @Param("example") SysDicitemExample example);

    int updateByExample(@Param("record") SysDicitem record, @Param("example") SysDicitemExample example);

    int updateByPrimaryKeySelective(SysDicitem record);

    int updateByPrimaryKey(SysDicitem record);
}
