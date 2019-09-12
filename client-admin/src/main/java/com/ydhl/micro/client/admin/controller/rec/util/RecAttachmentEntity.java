package com.ydhl.micro.client.admin.controller.rec.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName RecAttachmentEntity
 * @Description 上传图片、视频
 * @Author  miracle
 * @Date  2019/6/4 16:31
 * @Version 1.0
 */
@ApiModel(value = "卖车上传图片、视频")
@Data
@ToString
public class RecAttachmentEntity {

    @ApiModelProperty(value = "附件原始名称", example = "NORMAL")
    private String originalName;

    @ApiModelProperty(value = "附件名", example = "file.ico")
    private String fileName;

    @ApiModelProperty(value = "附件地址", example = "E:/upload/**/*")
    private String fileUrl;

    @ApiModelProperty(value = "浏览附件地址", example = "E:/upload/**/*")
    private String lookUrl;

    @ApiModelProperty(value = "附件类型PIC 图片，VIDEO视频", example = "PIC")
    private String fileType;

    @ApiModelProperty(value = "附件类型PIC 图片，VIDEO视频", example = "PIC")
    private String fileMoudle;

    @ApiModelProperty(value = "附件大小", example = "1024")
    private Double fileSize;







}
