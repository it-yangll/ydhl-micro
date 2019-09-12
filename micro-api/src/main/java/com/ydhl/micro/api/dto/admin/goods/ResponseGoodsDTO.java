package com.ydhl.micro.api.dto.admin.goods;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.metadata.BaseRowModel;
import com.ydhl.micro.api.dto.admin.goods.attachment.PmsAttachmentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
  *商品查询
  *@Author: yangll
  *@Date: 2019/5/5 9:57
  *@Version: 1.0
  */
@ApiModel(value = "商品查询DTO")
@Data
@ToString
public class ResponseGoodsDTO extends BaseRowModel {

    @ApiModelProperty(value = "商品主键")
    private Long id;

    @ApiModelProperty(value = "商品种类ID")
    private Long categoryId;

    @ApiModelProperty(value = "商品种类名称")
    private String categoryName;

    @ApiModelProperty(value = "车架号")
    private String vin;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品副标题", example = "SR600重型叉车L")
    private String subTitle;

    @ApiModelProperty(value = "商品标签,用空格分割", example = "SR600,重型,叉车,L")
    private String labels;

    @ApiModelProperty(value = "商品标签,用逗号分割", example = "SR600,重型,叉车,L")
    private String[] labelsShow;

    @ApiModelProperty(value = "关键字", example = "SR600")
    private String keywords;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "促销标识：1 促销(true);0默认(false)",example = "促销价格必须在促销标识为true的情况下允许编辑")
    private Boolean promotionState;

    @ApiModelProperty(value = "促销标识：1 促销(true);0默认(false)",example = "促销价格必须在促销标识为true的情况下允许编辑")
    private String promotionStateName;

    @ApiModelProperty(value = "促销地区")
    private String smsArea;

    @ApiModelProperty(value = "促销地区编码 空格分隔")
    private String smsAreaCode;

    @ApiModelProperty(value = "定金支付比例", hidden = true, example = "屏蔽字段")
    private Integer prepaidRatio;

    @ApiModelProperty(value = "吨位")
    private String tonnage;

    @ApiModelProperty(value = "动力方式")
    private String powerMode;

    @ApiModelProperty(value = "发动机")
    private String engine;

    @ApiModelProperty(value = "门架高度")
    private String gantryHeight;

    @ApiModelProperty(value = "轮胎")
    private String tyre;

    @ApiModelProperty(value = "使用年限")
    private String tenureofuse;

    @ApiModelProperty(value = "工作小时")
    private Integer workingHours;

    @ApiModelProperty(value = "车源地")
    private String sourceRegion;

    @ApiModelProperty(value = "工况", example = "工况")
    private String workState;

    @ApiModelProperty(value = "货叉长度", example = "货叉长度")
    private String forkLength;

    @ApiModelProperty(value = "属具，(随车工具)", example = "(随车工具)")
    private String carryTools;

    @ApiModelProperty(value = "商品状态0:未上架，1：已上架，2：已售出")
    private Integer state;

    @ApiModelProperty(value = "商品状态中文描述")
    private String stateName;

    @ApiModelProperty(value = "商品介绍", example = "进出口贸易叉车")
    private String introductions;

    @ApiModelProperty(value = "商品规格参数", example = "规格")
    private String attr;

    @ApiModelProperty(value = "商品封面图片", example = "http://10.2.32.124:20195/goods/images/201905/1558488741420_right.jpg")
    @NotBlank(message = "商品封面展示图片不允许为空")
    private String picUrl;

    @ApiModelProperty(value = "商品附件")
    List<PmsAttachmentDto> pmsAttachmentDtoList;

    @ApiModelProperty(value = "促销日期")
    private String smsTime;

    @ApiModelProperty(value = "商品附件（分开的）")
    public Map<String,List<PmsAttachmentDto>> getAttachments(){
        Map<String,List<PmsAttachmentDto>> result = new LinkedHashMap<>();
        if(CollectionUtil.isNotEmpty(pmsAttachmentDtoList)){
            pmsAttachmentDtoList.forEach(attachment->{
                if(StringUtils.isNotBlank(attachment.getFileMoudle())){
                    if(result.containsKey(attachment.getFileMoudle())){
                        List<PmsAttachmentDto> attachments = result.get(attachment.getFileMoudle());
                        PmsAttachmentDto newAttachment = new PmsAttachmentDto();
                        BeanUtil.copyProperties(attachment,newAttachment);
                        attachments.add(newAttachment);
                    }else{
                        List<PmsAttachmentDto> attachments = new ArrayList<>();
                        PmsAttachmentDto newAttachment = new PmsAttachmentDto();
                        BeanUtil.copyProperties(attachment,newAttachment);
                        attachments.add(newAttachment);
                        result.put(attachment.getFileMoudle(),attachments);
                    }
                }
            });
        }
        return result;
    }

    @ApiModelProperty(value = "商品显示价格，例如1.23万")
    public BigDecimal getDisplayPrice(){ if(price != null) return price.divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP);return null; }

    @ApiModelProperty(value = "商品标签，前台是展示使用 return []")
    public String[] getLabelsShow(){ if(StringUtils.isNotBlank(this.labels)) return this.labels.split(" ");return null; }

    @ApiModelProperty(value = "规格参数")
    public List<String> getAttrs(){
        return StringUtils.isBlank(attr)?null:new ArrayList<>(Arrays.asList(attr.split("\n")));
    }

}
