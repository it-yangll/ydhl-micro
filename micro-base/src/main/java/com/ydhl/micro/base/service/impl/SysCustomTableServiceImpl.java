package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;
import com.ydhl.micro.base.dao.auto.SysCustomTablesMapper;
import com.ydhl.micro.base.entity.SysCustomTables;
import com.ydhl.micro.base.entity.SysCustomTablesExample;
import com.ydhl.micro.base.service.SysCustomTableService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysCustomTableServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/8 12:58
 * @Version 1.0
 **/
@Service
public class SysCustomTableServiceImpl implements SysCustomTableService {

    @Autowired
    private SysCustomTablesMapper mapper;

    @Override
    public String findById(String tableId) {
        Long userId = JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk();
        SysCustomTablesExample exp = new SysCustomTablesExample();
        exp.createCriteria().andUserIdEqualTo(userId).andTableIdEqualTo(tableId);
        List<SysCustomTables> list = mapper.selectByExample(exp);
        if (CollectionUtil.isEmpty(list)) {
            return "";
        } else {
            return list.get(0).getShowColumns();
        }
    }

    @Override
    public void customized(CreateCustomTableDTO dto) {
        Long userId = JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk();
        SysCustomTablesExample exp = new SysCustomTablesExample();
        exp.createCriteria().andUserIdEqualTo(userId).andTableIdEqualTo(dto.getTableId());
        if (StringUtils.isBlank(dto.getShowColumns())) {
            mapper.deleteByExample(exp);
        } else {
            List<SysCustomTables> list = mapper.selectByExample(exp);
            if (CollectionUtil.isEmpty(list)) {
                SysCustomTables record = new SysCustomTables();
                BeanUtil.copyProperties(dto, record);
                record.setCreateId(userId);
                record.setCreateTime(new Date());
                record.setModifyId(userId);
                record.setModifyTime(new Date());
                record.setUserId(userId);
                mapper.insertSelective(record);
            } else {
                SysCustomTables record = list.get(0);
                BeanUtil.copyProperties(dto, record);
                record.setModifyId(userId);
                record.setModifyTime(new Date());
                mapper.updateByPrimaryKeySelective(record);
            }
        }
    }

}
