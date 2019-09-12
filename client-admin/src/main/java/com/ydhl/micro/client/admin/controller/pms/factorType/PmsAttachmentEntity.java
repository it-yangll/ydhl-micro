package com.ydhl.micro.client.admin.controller.pms.factorType;

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
@ApiModel(value = "附件上传Entity")
@Data
@ToString
public class PmsAttachmentEntity {

    @ApiModelProperty(value = "附件原始名称", example = "NORMAL")
    private String originalName;

    @ApiModelProperty(value = "附件名", example = "file.ico")
    private String fileName;

    @ApiModelProperty(value = "附件地址", example = "E:/upload/**/*")
    private String fileUrl;

    @ApiModelProperty(value = "附件类型PIC 图片，VIDEO视频", example = "PIC")
    private String fileType;

    @ApiModelProperty(value = "附件大小", example = "1024")
    private Double fileSize;







}
