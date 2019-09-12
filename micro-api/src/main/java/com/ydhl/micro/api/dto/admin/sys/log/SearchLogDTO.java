package com.ydhl.micro.api.dto.admin.sys.log;

import com.ydhl.micro.api.dto.common.SearchDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName SearchDemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 14:02
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "日志查询实体")
public class SearchLogDTO extends SearchDTO {

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "操作人")
    private String userName;

    @ApiModelProperty(value = "操作模块")
    private String module;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "说明；模糊查询")
    private String remark;

    @ApiModelProperty(value = "起止时间")
    private String createTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
