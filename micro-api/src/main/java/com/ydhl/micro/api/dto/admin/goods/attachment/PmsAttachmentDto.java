package com.ydhl.micro.api.dto.admin.goods.attachment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @Author: yangll
 * @description
 * @Date: 2019/5/7
 * @Version 1.0
 */
@ApiModel(value = "附件上传DTO")
@Data
@ToString
public class PmsAttachmentDto {

    @ApiModelProperty(value = "主键，自增", example = "1")
    private Long id;

    @ApiModelProperty(value = "商品ID", example = "1")
    private String goodsId;

    @ApiModelProperty(value = "附件原始名称", example = "NORMAL")
    private String originalName;

    @ApiModelProperty(value = "附件名", example = "file.ico")
    private String fileName;

    @ApiModelProperty(value = "附件地址", example = "E:/upload/**/*")
    private String fileUrl;

    @ApiModelProperty(value = "附件类型PIC 图片，VIDEO视频", example = "PIC")
    private String fileType;

    @ApiModelProperty(value = "附件所属模块整体外观、动力、铭牌、轮胎等", example = "德国马牌")
    private String fileMoudle;

    @ApiModelProperty(value = "附件大小", example = "1024")
    private String fileSize;

    @ApiModelProperty(value = "前台所需文件名称")
    private String name;

    @ApiModelProperty(value = "前台所需文件路径")
    private String url;

    public String getName() {
        return this.originalName;
    }

}
