package com.ydhl.micro.base.dao.auto;

import com.ydhl.micro.base.entity.SysMsgTemplate;
import com.ydhl.micro.base.entity.SysMsgTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMsgTemplateMapper {
    long countByExample(SysMsgTemplateExample example);

    int deleteByExample(SysMsgTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMsgTemplate record);

    int insertSelective(SysMsgTemplate record);

    List<SysMsgTemplate> selectByExample(SysMsgTemplateExample example);

    SysMsgTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMsgTemplate record, @Param("example") SysMsgTemplateExample example);

    int updateByExample(@Param("record") SysMsgTemplate record, @Param("example") SysMsgTemplateExample example);

    int updateByPrimaryKeySelective(SysMsgTemplate record);

    int updateByPrimaryKey(SysMsgTemplate record);
}
