package com.ydhl.micro.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.goods.label.ResponseLabelDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ResponseDicitemDTO;
import com.ydhl.micro.core.search.dao.GoodsRepository;
import com.ydhl.micro.core.search.entity.GoodsIdx;
import com.ydhl.micro.core.search.entity.PmsGoodsEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName GoodsIndexDao
 * @Description 商品索引维护工具类
 * @Author yangll
 * @Date 2019/6/27 18:31
 * @Version 1.0
 **/
@Component
public class GoodsIndexHelper {

    @Autowired
    private CacheHelper cacheHelper;

    private GoodsRepository goodsRepository;

    /**
     * @param id :商品id
     * @return GoodsIdx :
     * @Description //获取商品索引
     * @Author Ly
     * @Date 2019/6/27 18:40
     **/
    public GoodsIdx get(String id) {
//        Optional<GoodsIdx> optionalGoodsIdx = goodsRepository.findById(id);
        Optional<GoodsIdx> optionalGoodsIdx = null;
        GoodsIdx goodsIdx = null;
        if (optionalGoodsIdx.isPresent()) {
            goodsIdx = optionalGoodsIdx.get();
        } else {
            goodsIdx = new GoodsIdx();
        }
        return goodsIdx;
    }

    /**
     * @param goods :商品信息
     * @return void :
     * @Description //刷新商品索引
     * @Author Ly
     * @Date 2019/6/27 18:40
     **/
    public void flushIndex(PmsGoodsEntity goods) {
//        Optional<GoodsIdx> optionalGoodsIdx = goodsRepository.findById(goods.getId().toString());
        Optional<GoodsIdx> optionalGoodsIdx = null;
        GoodsIdx goodsIdx = null;
        if (optionalGoodsIdx.isPresent()) {
            goodsIdx = optionalGoodsIdx.get();
        } else {
            goodsIdx = new GoodsIdx();
        }
        BeanUtil.copyProperties(goods, goodsIdx);
        goodsIdx.setSmsFlag(goods.getPromotionState());
        goodsIdx.setSmsPrice(goods.getPromotionPrice());
        /** 商品索引中文维护 */
        /** 商品分类 */
        if (goods.getCategoryId() != null) {
            goodsIdx.setCategoryName(cacheHelper.getGoodsCategoryIdMap().get(goods.getCategoryId().toString()).getName());
        }

        /** 标签 */
        if (StringUtils.isNotBlank(goods.getLabels())) {
            Map<String, ResponseLabelDTO> labelDTOMap = cacheHelper.getGoodsLabelCodeMap();
            String[] labels = goods.getLabels().split(" ");
            List<String> labelNames = new ArrayList<>();
            for (String label : labels) {
                labelNames.add(labelDTOMap.get(label).getName());
            }
            goodsIdx.setLabelNames(CollectionUtil.join(labelNames, " "));
        }

        /** 获取缓存数据数据字段值 */
        Map<String, ResponseDicitemDTO> dictItemCodeMap = cacheHelper.getDictItemCodeMap();
        /** 吨位 */
        if (StringUtils.isNotBlank(goodsIdx.getTonnage())) {
            String tonnage = dictItemCodeMap.get(goodsIdx.getTonnage()).getItemValue();
            goodsIdx.setTonnageDesc(tonnage);
        }

        /** 动力方式 */
        if (StringUtils.isNotBlank(goodsIdx.getPowerMode())) {
            String powermode = dictItemCodeMap.get(goodsIdx.getPowerMode()).getItemValue();
            goodsIdx.setPowerModeDesc(powermode);
        }

        /** 动力方式 */
        if (StringUtils.isNotBlank(goodsIdx.getEngine())) {
            String engine = dictItemCodeMap.get(goodsIdx.getEngine()).getItemValue();
            goodsIdx.setEngineDesc(engine);
        }

        /** 门架高度 */
        if (StringUtils.isNotBlank(goodsIdx.getGantryHeight())) {
            String gantryHeight = dictItemCodeMap.get(goodsIdx.getGantryHeight()).getItemValue();
            goodsIdx.setGantryHeightDesc(gantryHeight);
        }

        /** 轮胎 */
        if (StringUtils.isNotBlank(goodsIdx.getTyre())) {
            String tyre = dictItemCodeMap.get(goodsIdx.getTyre()).getItemValue();
            goodsIdx.setTyreDesc(tyre);
        }

        /** 使用年限 */
        if (StringUtils.isNotBlank(goodsIdx.getTenureofuse())) {
            String tenureofuse = dictItemCodeMap.get(goodsIdx.getTenureofuse()).getItemValue();
            goodsIdx.setTenureofuseDesc(tenureofuse);
        }

        /** 车源地 */
        if (StringUtils.isNotBlank(goodsIdx.getSourceRegion())) {
            String sourceRegion = dictItemCodeMap.get(goodsIdx.getSourceRegion()).getItemValue();
            goodsIdx.setSourceRegionDesc(sourceRegion);
        }

        /** 工况 */
        if (StringUtils.isNotBlank(goodsIdx.getWorkState())) {
            String workState = dictItemCodeMap.get(goodsIdx.getWorkState()).getItemValue();
            goodsIdx.setWorkStateDesc(workState);
        }

        /** 货叉长度 */
        if (StringUtils.isNotBlank(goodsIdx.getForkLength())) {
            String forkLength = dictItemCodeMap.get(goodsIdx.getForkLength()).getItemValue();
            goodsIdx.setForkLengthDesc(forkLength);
        }

        /** 属具 */
        if (StringUtils.isNotBlank(goodsIdx.getCarryTools())) {
            String carryTools = dictItemCodeMap.get(goodsIdx.getCarryTools()).getItemValue();
            goodsIdx.setCarryToolsDesc(carryTools);
        }

        /** 保存商品索引信息 */
//        goodsRepository.save(goodsIdx);
    }

    /**
     * @param id :商品ID
     * @return void :
     * @Description //根据商品id删除索引
     * @Author Ly
     * @Date 2019/6/27 18:40
     **/
    public void deleteIndex(String id) {

//        goodsRepository.deleteById(id);
    }
}
