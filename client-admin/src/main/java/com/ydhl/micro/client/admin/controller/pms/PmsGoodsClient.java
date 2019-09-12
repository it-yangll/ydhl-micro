package com.ydhl.micro.client.admin.controller.pms;

import com.ydhl.micro.api.dto.admin.goods.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.controller.pms.factorType.PmsAttachmentEntity;
import com.ydhl.micro.client.admin.controller.pms.util.UploadFile;
import com.ydhl.micro.client.admin.service.pms.PmsGoodsFeignClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
  *商品管理APIClient
  *@Author: yangll
  *@Date: 2019/5/5 9:35
  *@Version: 1.0
  */
@RestController
@Slf4j
public class PmsGoodsClient {

    @Autowired
    private PmsGoodsFeignClient pmsGoodsFeignClient;

    @Autowired
    private UploadFile uploadFileClient;

    /**
     *@Description: 单条查询
     *@Param: [id]
     *@method: findById
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "单条查询")
    @RequestMapping(value = "goods/{id}", method = RequestMethod.GET)
    public HttpResultDTO findById(@PathVariable("id") Long id) {
        return pmsGoodsFeignClient.findById(id);
    }

    /**
     *@Description: 分页条件查询
     *@Param: [searchDTO]
     *@method: pageSearch
     *@return: com.ybzt.micro.api.dto.common.HttpPageResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "goods/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@RequestBody SearchGoodsPageDTO searchDTO) {
        return pmsGoodsFeignClient.pageSearch(searchDTO);
    }

    /**
     *@Description: 条件查询
     *@Param: [searchDTO]
     *@method: search
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "条件查询")
    @RequestMapping(value = "goods/data", method = RequestMethod.POST)
    public HttpResultDTO search(@RequestBody SearchGoodsDTO searchDTO) {
        return pmsGoodsFeignClient.search(searchDTO);
    }

    /**
     *@Description: 新增
     *@Param: [dto]
     *@method: create
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "添加商品")
    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public HttpResultDTO create(@Valid @RequestBody CreateGoodsDTO dto) {
        return pmsGoodsFeignClient.create(dto);
    }

    /**
     *@Description: 修改
     *@Param: [dto]
     *@method: modify
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "goods/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@Valid @RequestBody ModifyGoodsDTO dto) {
        return pmsGoodsFeignClient.modify(dto);
    }

    /**
     *@Description: 批量操作-上架
     *@Param: [idList]
     *@method: upperShelf
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/29
     */
    @ApiOperation(value = "批量操作-上架")
    @RequestMapping(value = "goods/upperShelf", method = RequestMethod.POST)
    public HttpResultDTO upperShelf(@RequestBody(required = true) List<Long> idList) {
        return pmsGoodsFeignClient.upperShelf(idList);
    }

    /**
     *@Description: 批量操作-下架
     *@Param: [idList]
     *@method: lowerShelf
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作商品下架")
    @RequestMapping(value = "goods/lowerShelf", method = RequestMethod.POST)
    public HttpResultDTO lowerShelf(@RequestBody(required = true) List<Long> idList) {
        return pmsGoodsFeignClient.lowerShelf(idList);
    }

    /**
     *@Description: 批量操作-删除
     *@Param: [idList]
     *@method: delete
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作删除 ")
    @RequestMapping(value = "goods/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@RequestBody(required = true) List<Long> idList){
        return pmsGoodsFeignClient.delete(idList);
    }

    /**
     *@Description: 批量操作-促销
     *@Param: [idList]
     *@method: delete
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作促销 ")
    @RequestMapping(value = "goods/promotionGoods", method = RequestMethod.POST)
    public HttpResultDTO isPromotionGoods(@RequestBody(required = true) List<Long> idList){
        return pmsGoodsFeignClient.isPromotionGoods(idList);
    }

    /**
     *@Description: 获取车架号对应参数
     *@Param: [vin]
     *@method: getVinConf
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/31
     */
    @ApiOperation(value = "获取车架号对应参数 ")
    @RequestMapping(value = "goods/getVinConf/{vin}", method = RequestMethod.GET)
    public HttpResultDTO getVinConf(@PathVariable(name = "vin") String vin){
        return pmsGoodsFeignClient.getVinConf(vin);

    }


    /**
     *@Description: 批量上传-文件
     *@Param: [files]
     *@method: uploadFile
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "商品文件图片上传")
    @RequestMapping(value = "public/goods/uploadFile", method = RequestMethod.POST)
    public HttpResultDTO uploadFile(@RequestPart(value = "files",required = false) MultipartFile[] files){
        List<PmsAttachmentEntity> fileList = null;
        if(files != null){
            fileList = uploadFileClient.uploadMultipleFileHandler(files);
            log.info("文件列表【{}】",fileList);
            if(fileList.size() > 0){
                return HttpResultDTO.ok(fileList);
            }else{
                return HttpResultDTO.error(null);
            }

        }
        return HttpResultDTO.error(null);

    }

    @ApiOperation(value = "取消促销 ")
    @RequestMapping(value = "goods/cancelPromotion", method = RequestMethod.POST)
    public HttpResultDTO cancelPromotion(@RequestBody(required = true) List<Long> idList){
        return pmsGoodsFeignClient.cancelPromotion(idList);
    }

    @ApiOperation(value = "设置促销时间 ")
    @RequestMapping(value = "goods/setSmsTime", method = RequestMethod.POST)
    public HttpResultDTO setSmsTime(@Valid @RequestBody SetSmsTimeDTO dto){
        return pmsGoodsFeignClient.setSmsTime(dto);
    }

    @ApiOperation(value = "设置促销地区 ")
    @RequestMapping(value = "goods/setSmsArea", method = RequestMethod.POST)
    public HttpResultDTO setSmsArea(@Valid @RequestBody SetSmsAreaDTO dto){
        return pmsGoodsFeignClient.setSmsArea(dto);
    }


}
