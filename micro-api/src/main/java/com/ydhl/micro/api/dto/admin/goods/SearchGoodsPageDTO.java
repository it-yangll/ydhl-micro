package com.ydhl.micro.api.dto.admin.goods;

import com.ydhl.micro.api.dto.common.SearchDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  *商品分页查询业务参数
  *@Author: yangll
  *@Date: 2019/5/5 14:40
  *@Version: 1.0
  */
@Data
@ToString
public class SearchGoodsPageDTO extends SearchDTO {

    @ApiModelProperty(value = "商品主键", example = "1")
    private Long id;

    @ApiModelProperty(value = "商品种类ID", example = "9127487128481")
    private String categoryId;

    @ApiModelProperty(value = "车架号", example = "A18026888")
    private String vin;

    @ApiModelProperty(value = "商品名称", example = "SR600重型叉车")
    private String name;

    @ApiModelProperty(value = "商品关键字", example = "SR600")
    private String keywords;

    @ApiModelProperty(value = "吨位", example = "12T")
    private String tonnage;

    @ApiModelProperty(value = "动力方式", example = "混动")
    private String powerMode;

    @ApiModelProperty(value = "发动机", example = "三菱")
    private String engine;

    @ApiModelProperty(value = "门架高度", example = "3米5")
    private String gantryHeight;

    @ApiModelProperty(value = "使用年限", example = "3")
    private String tenureofuse;

    @ApiModelProperty(value = "轮胎", example = "德国马牌")
    private String tyre;

    @ApiModelProperty(value = "车源地", example = "中国柳工")
    private String sourceRegion;

    @ApiModelProperty(value = "工况", example = "工况")
    private String workState;

    @ApiModelProperty(value = "货叉长度", example = "货叉长度")
    private String forkLength;

    @ApiModelProperty(value = "属具，(随车工具)", example = "(随车工具)")
    private String carryTools;

    @ApiModelProperty(value = "工作小时", example = "18637")
    private String workingHours;

    @ApiModelProperty(value = "商品状态0:未上架，1：已上架，2：已售出", example = "0")
    private Integer state;

    @ApiModelProperty(value = "促销标识：1 促销(true);0默认(false)",example = "促销价格必须在促销标识为true的情况下允许编辑")
    private Boolean promotionState;


}
