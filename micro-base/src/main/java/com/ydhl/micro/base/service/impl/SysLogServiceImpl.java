package com.ydhl.micro.base.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.base.dao.auto.SysLogMapper;
import com.ydhl.micro.base.entity.SysLog;
import com.ydhl.micro.base.entity.SysLogExample;
import com.ydhl.micro.base.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysLogServiceImpl
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:46:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper logMapper;

    @Override
    public PageInfo<SysLog> pageSearch(SearchLogDTO searchDTO) {
        log.info("pageSearch:{}", searchDTO);

        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        Page<SysLog> logs = (Page<SysLog>) logMapper.selectByExample(createQuery(searchDTO));

        PageInfo<SysLog> result = new PageInfo(logs);
        return result;
    }

    private SysLogExample createQuery(SearchLogDTO dto) {
        SysLogExample exp = new SysLogExample();
        SysLogExample.Criteria cri = exp.createCriteria();

        if (StringUtils.isNotBlank(dto.getPlatform())) {
            cri.andPlatformLike("%" + dto.getPlatform() + "%");
        }

        if (StringUtils.isNotBlank(dto.getModule())) {
            cri.andModuleLike("%" + dto.getModule() + "%");
        }

        if (StringUtils.isNotBlank(dto.getOperation())) {
            cri.andOperationLike("%" + dto.getOperation() + "%");
        }

        if (StringUtils.isNotBlank(dto.getUserName())) {
            cri.andUserNameLike("%" + dto.getUserName() + "%");
        }

        if (StringUtils.isNotBlank(dto.getRemark())) {
            cri.andRemarkLike("%" + dto.getRemark() + "%");
        }

        if (StringUtils.isNotBlank(dto.getCreateTime())) {
            cri.andCreateTimeGreaterThanOrEqualTo(DateUtil.parse(dto.getCreateTime()).toJdkDate());
        }

        if (StringUtils.isNotBlank(dto.getEndTime())) {
            cri.andCreateTimeLessThanOrEqualTo(DateUtil.parse(dto.getEndTime()).toJdkDate());
        }

        exp.setOrderByClause("id desc");
        return exp;
    }
}
