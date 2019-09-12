package com.ydhl.micro.base.service.impl;

import com.ydhl.micro.base.dao.auto.SysMsgTemplateMapper;
import com.ydhl.micro.base.entity.SysMsgTemplate;
import com.ydhl.micro.base.service.SysMsgTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysMsgTemplateServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/27 17:39
 * @Version 1.0
 **/
@Slf4j
@Service
public class SysMsgTemplateServiceImpl implements SysMsgTemplateService {

    @Autowired
    private SysMsgTemplateMapper templateMapper;

    @Override
    public List<SysMsgTemplate> findAll() {
        return templateMapper.selectByExample(null);
    }

}
