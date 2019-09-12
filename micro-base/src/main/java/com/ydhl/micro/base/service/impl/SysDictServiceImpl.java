package com.ydhl.micro.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ResponseDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.api.enumcode.admin.AdminCodeEnum;
import com.ydhl.micro.api.enumcode.consts.State;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.dao.auto.SysDicitemMapper;
import com.ydhl.micro.base.dao.auto.SysDictypeMapper;
import com.ydhl.micro.base.entity.SysDicitem;
import com.ydhl.micro.base.entity.SysDicitemExample;
import com.ydhl.micro.base.entity.SysDictype;
import com.ydhl.micro.base.service.SysDictService;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.CommonUtil;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysDictServiceImpl
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 22:19
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictypeMapper dictypeMapper;
    @Autowired
    private SysDicitemMapper mapper;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public List<SysDictype> findAll() {
        return dictypeMapper.selectByExample(null);
    }

    @Override
    public List<ResponseDicitemDTO> list() {
        List<SysDicitem> dicitems = mapper.selectByExample(null);
        List<ResponseDicitemDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(dicitems)) {
            dicitems.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    @Override
    public ResponseDicitemDTO findById(Long id) {
        return trans2DTO(mapper.selectByPrimaryKey(id));
    }

    private ResponseDicitemDTO trans2DTO(SysDicitem dicitem) {
        ResponseDicitemDTO responseDicitemDTO = new ResponseDicitemDTO();
        BeanUtil.copyProperties(dicitem, responseDicitemDTO);
        responseDicitemDTO.setStateName(State.valueOf(dicitem.getState()).getShowText());
        responseDicitemDTO.setPresetName(dicitem.getPreset() ? "是" : "否");
        return responseDicitemDTO;
    }

    @Override
    public PageInfo<ResponseDicitemDTO> pageSearch(SearchDicitemDTO searchDTO) {
        log.info("pageSearch:{}", searchDTO);

        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        Page<SysDicitem> dicitems = (Page<SysDicitem>) mapper.selectByExample(createQuery(searchDTO));

        List<ResponseDicitemDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(dicitems)) {
            dicitems.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }

        PageInfo<ResponseDicitemDTO> result = new PageInfo(dicitems);
        result.setList(dtos);
        return result;
    }

    @Override
    public List<ResponseDicitemDTO> search(SearchDicitemDTO searchDTO) {
        log.info("search:{}", searchDTO);
        List<SysDicitem> dicitems = mapper.selectByExample(createQuery(searchDTO));
        List<ResponseDicitemDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(dicitems)) {
            dicitems.forEach(u -> {
                dtos.add(trans2DTO(u));
            });
        }
        return dtos;
    }

    private SysDicitemExample createQuery(SearchDicitemDTO dto) {
        SysDicitemExample exp = new SysDicitemExample();
        SysDicitemExample.Criteria cri = exp.createCriteria();
        cri.andStateNotEqualTo(State.DELETED.name());

        if (StringUtils.isNotBlank(dto.getTypeCode())) {
            cri.andTypeCodeEqualTo(dto.getTypeCode());
        }

        if (StringUtils.isNotBlank(dto.getItemCode())) {
            cri.andItemCodeLike("%" + dto.getItemCode() + "%");
        }

        if (StringUtils.isNotBlank(dto.getItemValue())) {
            cri.andItemValueLike("%" + dto.getItemValue() + "%");
        }

        /** 排序 */
        String order = "";
        if (StringUtils.isNotBlank(dto.getSortBy())) {
            order = order.concat(dto.getSortBy());

            if (StringUtils.isNotBlank(dto.getOrderBy())) {
                order = order.concat(" ").concat(dto.getOrderBy());
            }
        }
        if (StringUtils.isNotBlank(order)) {
            exp.setOrderByClause(order);
        } else {
            exp.setOrderByClause("sort asc");
        }
        return exp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateDicitemDTO dto) {
        SysDicitemExample exp = new SysDicitemExample();
        exp.createCriteria().andItemCodeEqualTo(dto.getItemCode());
        if (CollectionUtil.isNotEmpty(mapper.selectByExample(exp))) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_DICITEM_REPEAT, cacheHelper.msgTemplate(AdminCodeEnum.ERR_DICITEM_REPEAT));
        }
        SysDicitem record = new SysDicitem();
        BeanUtil.copyProperties(dto, record);
        record.setState(State.NORMAL.name());
        record.setCreateId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setCreateTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        record.setModifyTime(new Date());
        record.setPreset(false);
        mapper.insertSelective(record);
        cacheHelper.saveDict(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyDicitemDTO dto) {
        SysDicitem record = mapper.selectByPrimaryKey(dto.getId());
        if (record.getPreset()) {
            throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
        }
        BeanUtil.copyProperties(dto, record);
        record.setModifyTime(new Date());
        record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        mapper.updateByPrimaryKeySelective(record);
        cacheHelper.saveDict(trans2DTO(record));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        idList.forEach(id -> {
            SysDicitem record = mapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setState(State.DELETED.name());
            record.setItemCode(CommonUtil.appendDeleteUnique(record.getItemCode()));
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDict(trans2DTO(record));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void frozen(List<Long> idList) {
        idList.forEach(id -> {
            SysDicitem record = mapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setState(State.FROZEN.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDict(trans2DTO(record));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> idList) {
        idList.forEach(id -> {
            SysDicitem record = mapper.selectByPrimaryKey(id);
            if (record.getPreset()) {
                throw new SystemRuntimeException(AdminCodeEnum.ERR_EDIT_PRESET, cacheHelper.msgTemplate(AdminCodeEnum.ERR_EDIT_PRESET));
            }
            record.setState(State.NORMAL.name());
            record.setModifyId(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
            record.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(record);
            cacheHelper.saveDict(trans2DTO(record));
        });
    }
}
