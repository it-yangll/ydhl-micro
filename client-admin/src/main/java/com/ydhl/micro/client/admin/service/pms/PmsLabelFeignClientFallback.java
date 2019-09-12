package com.ydhl.micro.client.admin.service.pms;

import com.ydhl.micro.api.dto.admin.goods.label.CreateLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.ModifyLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
  *商品标签管理接口调用
  *@Author: yangll
  *@Date: 2019/5/5 11:20
  *@Version: 1.0
  */
@Component
public class PmsLabelFeignClientFallback implements PmsLabelFeignClient {


    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchLabelPageDTO searchDTO) {
        return HttpPageResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO search(SearchLabelDTO searchDTO) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO create(@Valid CreateLabelDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO modify(@Valid ModifyLabelDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO delete(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}

