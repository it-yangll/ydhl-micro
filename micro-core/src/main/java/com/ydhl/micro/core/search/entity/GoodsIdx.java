package com.ydhl.micro.core.search.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GoodsIdx
 * @Description 商品全文检索索引对象
 * @Author yangll
 * @Date 2019-9-10 13:47:23
 * @Version 1.0
 * No Do
 **/
//@Document(indexName = "heli", type = "goods", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@ToString
public class GoodsIdx {

    //@Id
    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "是否是促销商品")
    //@Field(type = FieldType.Boolean)
    private boolean smsFlag = false;

    @ApiModelProperty(value = "商品图片")
    //@Field(type = FieldType.Keyword, index = false)
    private String picUrl;

    @ApiModelProperty(value = "商品种类id")
    //@Field(type = FieldType.Long)
    private Long categoryId;

    @ApiModelProperty(value = "商品种类名称")
    //@Field(type = FieldType.Keyword)
    private String categoryName;

    @ApiModelProperty(value = "车架号")
    //@Field(type = FieldType.Keyword)
    private String vin;

    @ApiModelProperty(value = "商品名称")
    //@Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    //@Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String subTitle;

    @ApiModelProperty(value = "商品标签 空格分隔")
    //@Field(type = FieldType.Text, searchAnalyzer = "whitespace", analyzer = "whitespace")
    private String labels;

    @ApiModelProperty(value = "商品标签名称 空格分隔")
    //@Field(type = FieldType.Text, searchAnalyzer = "whitespace", analyzer = "whitespace")
    private String labelNames;

    @ApiModelProperty(value = "商品关键字")
    //@Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String keywords;

    @ApiModelProperty(value = "吨位")
    //@Field(type = FieldType.Keyword)
    private String tonnage;

    @ApiModelProperty(value = "吨位描述")
    //@Field(type = FieldType.Keyword)
    private String tonnageDesc;

    @ApiModelProperty(value = "动力方式")
    //@Field(type = FieldType.Keyword)
    private String powerMode;

    @ApiModelProperty(value = "动力方式描述")
    //@Field(type = FieldType.Keyword)
    private String powerModeDesc;

    @ApiModelProperty(value = "发动机")
    //@Field(type = FieldType.Keyword)
    private String engine;

    @ApiModelProperty(value = "发动机描述")
    //@Field(type = FieldType.Keyword)
    private String engineDesc;

    @ApiModelProperty(value = "门架高度")
    //@Field(type = FieldType.Keyword)
    private String gantryHeight;

    @ApiModelProperty(value = "门架高度描述")
    //@Field(type = FieldType.Keyword)
    private String gantryHeightDesc;

    @ApiModelProperty(value = "使用年限")
    //@Field(type = FieldType.Keyword)
    private String tenureofuse;

    @ApiModelProperty(value = "使用年限描述")
    //@Field(type = FieldType.Keyword)
    private String tenureofuseDesc;

    @ApiModelProperty(value = "轮胎")
    @Field(type = FieldType.Keyword)
    private String tyre;

    @ApiModelProperty(value = "轮胎描述")
    //@Field(type = FieldType.Keyword)
    private String tyreDesc;

    @ApiModelProperty(value = "车源地")
    //@Field(type = FieldType.Keyword)
    private String sourceRegion;

    @ApiModelProperty(value = "车源地描述")
    //@Field(type = FieldType.Keyword)
    private String sourceRegionDesc;

    @ApiModelProperty(value = "工况")
    //@Field(type = FieldType.Keyword)
    private String workState;

    @ApiModelProperty(value = "工况描述")
    //@Field(type = FieldType.Keyword)
    private String workStateDesc;

    @ApiModelProperty(value = "货叉长度")
    @Field(type = FieldType.Keyword)
    private String forkLength;

    @ApiModelProperty(value = "货叉长度描述")
    //@Field(type = FieldType.Keyword)
    private String forkLengthDesc;

    @ApiModelProperty(value = "属具")
    //@Field(type = FieldType.Keyword)
    private String carryTools;

    @ApiModelProperty(value = "属具描述")
    //@Field(type = FieldType.Keyword)
    private String carryToolsDesc;

    @ApiModelProperty(value = "工作小时")
    //@Field(type = FieldType.Integer)
    private Integer workingHours;

    @ApiModelProperty(value = "商品价格")
    //@Field(type = FieldType.Double)
    private BigDecimal price;

    @ApiModelProperty(value = "商品最低支付比例")
    //@Field(type = FieldType.Integer)
    private Integer prepaidRatio;

    @ApiModelProperty(value = "商品介绍")
    //@Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String introductions;

    @ApiModelProperty(value = "商品规格参数")
    //@Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String attr;

    @ApiModelProperty(value = "上架时间")
    //@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date shelfTime;

    @ApiModelProperty(value = "商品状态0:未上架，1：已上架，2:已购买，3：已售出")
    //@Field(type = FieldType.Integer)
    private Integer state;

    @ApiModelProperty(value = "促销价格")
    //@Field(type = FieldType.Double, index = false)
    private BigDecimal smsPrice;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "促销标识：1 促销(true);0默认(false)",example = "促销价格必须在促销标识为true的情况下允许编辑")
    private Boolean promotionState;

    @ApiModelProperty(value = "促销地区")
    //@Field(type = FieldType.Keyword, index = false)
    private String smsArea;

    @ApiModelProperty(value = "促销地区编码 空格分隔")
    //@Field(type = FieldType.Text, searchAnalyzer = "whitespace", analyzer = "whitespace")
    private String smsAreaCode;

    @ApiModelProperty(value = "促销开始时间")
    //@Field(type = FieldType.Date, index = false, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date smsStartTime;

    @ApiModelProperty(value = "促销结束时间")
    //@Field(type = FieldType.Date, index = false, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date smsEndTime;

    //@Field(type = FieldType.Long)
    private long smsStartTimeMillis;

    //@Field(type = FieldType.Long)
    private long smsEndTimeMillis;

    public long getSmsStartTimeMillis() {
        smsStartTimeMillis = smsStartTime == null ? 0L : smsStartTime.getTime();
        return smsStartTimeMillis;
    }

    public void setSmsStartTimeMillis(long smsStartTimeMillis) {
        this.smsStartTimeMillis = smsStartTime == null ? 0L : smsStartTime.getTime();
    }

    public long getSmsEndTimeMillis() {
        smsEndTimeMillis =  smsEndTime == null ? 0L : smsEndTime.getTime();
        return smsEndTimeMillis;
    }

    public void setSmsEndTimeMillis(long smsEndTimeMillis) {
        this.smsEndTimeMillis =  smsEndTime == null ? 0L : smsEndTime.getTime();
    }

}
