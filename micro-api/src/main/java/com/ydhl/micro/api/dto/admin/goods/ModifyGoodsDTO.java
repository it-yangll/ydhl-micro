package com.ydhl.micro.api.dto.admin.goods;

import com.ydhl.micro.api.dto.admin.goods.attachment.PmsAttachmentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
  *商品修改业务参数
  *@Author: yangll
  *@Date: 2019/5/5 9:38
  *@Version: 1.0
  */
@ApiModel("商品修改")
@Data
@ToString
public class ModifyGoodsDTO {

    @ApiModelProperty(value = "商品ID", example = "9127487128481")
    @NotNull(message = "商品ID参数为空.")
    private Long id;

    @ApiModelProperty(value = "商品种类ID", example = "9127487128481")
    @NotNull(message = "商品种类ID参数不允许为空.")
    private Long categoryId;

    @ApiModelProperty(value = "车架号", example = "A18026888")
    @NotBlank(message = "车架号参数不允许为空.")
    private String vin;

    @ApiModelProperty(value = "商品名称", example = "SR600重型叉车")
    @NotBlank(message = "商品名称参数不允许为空.")
    private String name;

    @ApiModelProperty(value = "商品副标题", example = "SR600重型叉车L")
    private String subTitle;

    @ApiModelProperty(value = "商品标签,用都好分割", example = "SR600,重型,叉车,L")
    private String labels;

    @ApiModelProperty(value = "商品关键字", example = "SR600")
    private String keywords;

    @ApiModelProperty(value = "商品状态0:未上架，1：已上架，2:已购买，3：已售出", example = "默认0")
    private Integer state;

    @ApiModelProperty(value = "吨位", example = "12T")
    @NotBlank(message = "吨位不允许为空.")
    private String tonnage;

    @ApiModelProperty(value = "动力方式", example = "混动")
    @NotBlank(message = "动力方式参数不允许为空.")
    private String powerMode;

    @ApiModelProperty(value = "发动机", example = "三菱")
    @NotBlank(message = "发动机参数不允许为空.")
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

    @ApiModelProperty(value = "商品价格", example = "91837.8")
    @NotNull(message = "商品价格参数不允许为空.")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "促销标识：1 促销(true);0默认(false)",example = "促销价格必须在促销标识为true的情况下允许编辑")
    private Boolean promotionState;

    @ApiModelProperty(value = "定金支付比例", hidden = true, example = "屏蔽字段")
    private Integer prepaidRatio;

    @ApiModelProperty(value = "商品介绍", example = "进出口贸易叉车")
    private String introductions;

    @ApiModelProperty(value = "商品规格参数", example = "规格")
    private String attr;

    @ApiModelProperty(value = "商品封面图片", example = "http://10.2.32.124:20195/goods/images/201905/1558488741420_right.jpg")
    @NotBlank(message = "商品封面展示图片不允许为空")
    private String picUrl;

    @ApiModelProperty(value = "商品附件", example = "商品附件.jpg or 商品附件.video")
    List<PmsAttachmentDto> pmsAttachmentDtoList;

    @ApiModelProperty(value = "商品标签数组形势传递")
    private String[] labelsArr;

}
