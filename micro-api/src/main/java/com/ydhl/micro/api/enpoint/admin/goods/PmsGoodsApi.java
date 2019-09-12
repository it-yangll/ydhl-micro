package com.ydhl.micro.api.enpoint.admin.goods;

import com.ydhl.micro.api.dto.admin.goods.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
  *商品接口转发
  *@Author: yangll
  *@Date: 2019/4/29 17:31
  *@Version: 1.0
  */
public interface PmsGoodsApi {

    /** 单条查询 */
    @RequestMapping(value = "goods/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    /** 分页条件查询 */
    @RequestMapping(value = "goods/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchGoodsPageDTO searchGoodsDTO);

    /** 条件查询 */
    @RequestMapping(value = "goods/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchGoodsDTO searchGoodsDTO);

    /** 新增 */
    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateGoodsDTO createGoodsDTO);

    /** 修改 */
    @RequestMapping(value = "goods/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyGoodsDTO dto);

    /** 批量操作-上架 */
    @RequestMapping(value = "goods/upperShelf", method = RequestMethod.POST)
    HttpResultDTO upperShelf(@RequestBody List<Long> idList);

    /** 批量操作-下架 */
    @RequestMapping(value = "goods/lowerShelf", method = RequestMethod.POST)
    HttpResultDTO lowerShelf(@RequestBody List<Long> idList);

    /** 批量操作-删除 */
    @RequestMapping(value = "goods/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-促销*/
    @RequestMapping(value = "goods/promotionGoods", method = RequestMethod.POST)
    HttpResultDTO isPromotionGoods(@RequestBody List<Long> idList);

    /** 获取车架号对应参数 */
    @RequestMapping(value = "goods/getVinConf/{vin}", method = RequestMethod.GET)
    HttpResultDTO getVinConf(@PathVariable(name = "vin") String vin);

    /** 批量上传-文件 */
    @RequestMapping(value = "public/goods/uploadFile", method = RequestMethod.POST)
    HttpResultDTO uploadFile(@RequestPart(value = "files",required = false) MultipartFile[] files);

    @RequestMapping(value = "goods/cancelPromotion", method = RequestMethod.POST)
    HttpResultDTO cancelPromotion(@RequestBody List<Long> idList);

    @RequestMapping(value = "goods/setSmsTime", method = RequestMethod.POST)
    HttpResultDTO setSmsTime(@Valid @RequestBody SetSmsTimeDTO dto);

    @RequestMapping(value = "goods/setSmsArea", method = RequestMethod.POST)
    HttpResultDTO setSmsArea(@Valid @RequestBody SetSmsAreaDTO dto);

}
