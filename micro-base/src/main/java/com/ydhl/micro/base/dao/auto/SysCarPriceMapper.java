package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysCarPrice;
import com.ydhl.micro.base.entity.SysCarPriceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCarPriceMapper {
    long countByExample(SysCarPriceExample example);

    int deleteByExample(SysCarPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCarPrice record);

    int insertSelective(SysCarPrice record);

    List<SysCarPrice> selectByExample(SysCarPriceExample example);

    SysCarPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCarPrice record, @Param("example") SysCarPriceExample example);

    int updateByExample(@Param("record") SysCarPrice record, @Param("example") SysCarPriceExample example);

    int updateByPrimaryKeySelective(SysCarPrice record);

    int updateByPrimaryKey(SysCarPrice record);
}
