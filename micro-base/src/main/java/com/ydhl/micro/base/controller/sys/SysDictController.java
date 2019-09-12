package com.ydhl.micro.base.controller.sys;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysDictApi;
import com.ydhl.micro.base.service.SysDictService;
import com.ydhl.micro.core.consts.ModuleEnum;
import com.ydhl.micro.core.consts.OperationEnum;
import com.ydhl.micro.core.util.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDictController
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 22:22
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysDictController implements SysDictApi {

    @Autowired
    private SysDictService service;

    @Override
    public HttpResultDTO findDicTypeList() {
        return HttpResultDTO.ok(service.findAll());
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.ok(service.findById(id));
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchDicitemDTO searchDTO) {
        PageInfo pageInfo = service.pageSearch(searchDTO);
        return HttpPageResultDTO.ok(pageInfo.getList(), pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal());
    }

    @Override
    public HttpResultDTO search(SearchDicitemDTO searchDTO) {
        return HttpResultDTO.ok(service.search(searchDTO));
    }

    @Override
    public HttpResultDTO getDictTypes() {
        List<String> list = new ArrayList<String>();
        list.add("tonnage");
        list.add("tenureofuse");
        list.add("engine");
        list.add("sourceRegion");
        return HttpResultDTO.ok();
    }

    @Override
    public HttpResultDTO getStarDictTypes() {
        List<String> list = new ArrayList<String>();
        list.add("tonnage");
        list.add("priceRange");
        list.add("gantryHeight");
        list.add("forkLength");
        list.add("powerMode");
        list.add("tenureofuse");
        list.add("carryTools");
        list.add("workState");
        /*List<ResponseLiteappDictsDTO> searchList = service.getDictTypes(list);
        List<Object> firstList = new ArrayList<Object>();
        firstList.add(searchList.get(0));
        firstList.add(searchList.get(1));
        List<Object> secondList = new ArrayList<Object>();
        secondList.add(searchList.get(2));
        secondList.add(searchList.get(3));
        List<Object> thirdList = new ArrayList<Object>();
        for (int i = 4; i < searchList.size(); i++) {
            thirdList.add(searchList.get(i));
        }*/
        List<Object> resultList = new ArrayList<Object>();
        /*resultList.add(firstList);
        resultList.add(secondList);
        resultList.add(thirdList);*/
        return HttpResultDTO.ok(resultList);
    }

    @Override
    @OperationLog(module = ModuleEnum.数据字典, operation = OperationEnum.新增, remark = "添加数据字典配置项")
    public HttpResultDTO create(CreateDicitemDTO dto) {
        service.create(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.数据字典, operation = OperationEnum.编辑, remark = "修改数据字典配置项")
    public HttpResultDTO modify(ModifyDicitemDTO dto) {
        service.modify(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.数据字典, operation = OperationEnum.删除, remark = "删除数据字典配置项")
    public HttpResultDTO delete(List<Long> idList) {
        service.delete(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.数据字典, operation = OperationEnum.冻结, remark = "冻结数据字典配置项")
    public HttpResultDTO frozen(List<Long> idList) {
        service.frozen(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.数据字典, operation = OperationEnum.启用, remark = "启用数据字典配置项")
    public HttpResultDTO enable(List<Long> idList) {
        service.enable(idList);
        return HttpResultDTO.ok();
    }
}
