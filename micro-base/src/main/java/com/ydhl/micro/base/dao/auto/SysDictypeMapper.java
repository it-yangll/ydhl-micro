package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysDictype;
import com.ydhl.micro.base.entity.SysDictypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictypeMapper {
    long countByExample(SysDictypeExample example);

    int deleteByExample(SysDictypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDictype record);

    int insertSelective(SysDictype record);

    List<SysDictype> selectByExample(SysDictypeExample example);

    SysDictype selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysDictype record, @Param("example") SysDictypeExample example);

    int updateByExample(@Param("record") SysDictype record, @Param("example") SysDictypeExample example);

    int updateByPrimaryKeySelective(SysDictype record);

    int updateByPrimaryKey(SysDictype record);
}
