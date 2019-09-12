package com.ydhl.micro.core.search.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表: pms_goods
 */
@Data
public class PmsGoodsEntity {
    /**
     * 是否允许NULL值:  不允许
     */
    private Long id;

    /**
     * 商品种类id
     *
     * 是否允许NULL值:  不允许
     */
    private Long categoryId;

    /**
     * 唯一标识
     *
     * 是否允许NULL值:  不允许
     */
    private String identityCode;

    /**
     * 车架号
     *
     * 是否允许NULL值:  不允许
     */
    private String vin;

    /**
     * 商品名称
     *
     * 是否允许NULL值:  不允许
     */
    private String name;

    /**
     * 商品副标题
     *
     * 是否允许NULL值:  允许
     */
    private String subTitle;

    /**
     * 商品图片
     *
     * 是否允许NULL值:  不允许
     */
    private String picUrl;

    /**
     * 商品标签 逗号分隔
     *
     * 是否允许NULL值:  允许
     */
    private String labels;

    /**
     * 商品关键字
     *
     * 是否允许NULL值:  允许
     */
    private String keywords;

    /**
     * 吨位
     *
     * 是否允许NULL值:  不允许
     */
    private String tonnage;

    /**
     * 动力方式
     *
     * 是否允许NULL值:  不允许
     */
    private String powerMode;

    /**
     * 发动机
     *
     * 是否允许NULL值:  不允许
     */
    private String engine;

    /**
     * 门架高度
     *
     * 是否允许NULL值:  允许
     */
    private String gantryHeight;

    /**
     * 使用年限
     *
     * 是否允许NULL值:  允许
     */
    private String tenureofuse;

    /**
     * 轮胎
     *
     * 是否允许NULL值:  允许
     */
    private String tyre;

    /**
     * 车源地
     *
     * 是否允许NULL值:  允许
     */
    private String sourceRegion;

    /**
     * 工况
     *
     * 是否允许NULL值:  允许
     */
    private String workState;

    /**
     * 货叉长度
     *
     * 是否允许NULL值:  允许
     */
    private String forkLength;

    /**
     * 属具，(随车工具)
     *
     * 是否允许NULL值:  允许
     */
    private String carryTools;

    /**
     * 工作小时
     *
     * 是否允许NULL值:  允许
     */
    private Integer workingHours;

    /**
     * 商品价格
     *
     * 是否允许NULL值:  不允许
     */
    private BigDecimal price;

    /**
     * 促销标识：1 促销(true);0默认(false)    促销价格必须在促销标识为true的情况下允许编辑
     *
     * 是否允许NULL值:  不允许
     */
    private Boolean promotionState;

    /**
     * 促销价格
     *
     * 是否允许NULL值:  允许
     */
    private BigDecimal promotionPrice;

    /**
     * 促销开始日期
     *
     * 是否允许NULL值:  允许
     */
    private Date smsStartTime;

    /**
     * 促销结束日期
     *
     * 是否允许NULL值:  允许
     */
    private Date smsEndTime;

    /**
     * 促销地区
     *
     * 是否允许NULL值:  允许
     */
    private String smsArea;

    /**
     * 促销地区编码
     *
     * 是否允许NULL值:  允许
     */
    private String smsAreaCode;

    /**
     * 定金支付比例
     *
     * 是否允许NULL值:  允许
     */
    private Integer prepaidRatio;

    /**
     * 商品介绍
     *
     * 是否允许NULL值:  允许
     */
    private String introductions;

    /**
     * 商品规格参数
     *
     * 是否允许NULL值:  允许
     */
    private String attr;

    /**
     * 上架时间
     *
     * 是否允许NULL值:  允许
     */
    private Date shelfTime;

    /**
     * 商品状态0:未上架，1：已上架，2:已购买，3：已售出
     *
     * 是否允许NULL值:  不允许
     */
    private Integer state;

    /**
     * 对应的订单id
     *
     * 是否允许NULL值:  允许
     */
    private Long orderId;

    /**
     * 创建人
     *
     * 是否允许NULL值:  不允许
     */
    private Long createId;

    /**
     * 创建时间
     *
     * 是否允许NULL值:  不允许
     */
    private Date createTime;

    /**
     * 修改人
     *
     * 是否允许NULL值:  不允许
     */
    private Long modifyId;

    /**
     * 最后修改时间
     *
     * 是否允许NULL值:  不允许
     */
    private Date modifyTime;
}
