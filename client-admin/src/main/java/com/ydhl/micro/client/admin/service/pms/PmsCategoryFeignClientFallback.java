package com.ydhl.micro.client.admin.service.pms;

import com.ydhl.micro.api.dto.admin.goods.category.CreateCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.ModifyCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
  *商品类型管理接口调用
  *@Author: yangll
  *@Date: 2019/5/5 11:20
  *@Version: 1.0
  */
@Component
public class PmsCategoryFeignClientFallback implements PmsCategoryFeignClient {


    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchCategoryPageDTO searchDTO) {
        return HttpPageResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO search(SearchCategoryDTO searchDTO) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO searchList() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO create(@Valid CreateCategoryDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO modify(@Valid ModifyCategoryDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO delete(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}

