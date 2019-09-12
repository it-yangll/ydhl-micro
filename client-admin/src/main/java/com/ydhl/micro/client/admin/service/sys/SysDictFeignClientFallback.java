package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDictFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 22:09
 * @Version 1.0
 **/
@Component
public class SysDictFeignClientFallback implements SysDictFeignClient {

    @Override
    public HttpResultDTO findDicTypeList() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchDicitemDTO searchDTO) {
        return HttpPageResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO search(SearchDicitemDTO searchDTO) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO create(@Valid CreateDicitemDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO modify(@Valid ModifyDicitemDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO delete(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO frozen(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO enable(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO getDictTypes() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO getStarDictTypes() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
