package com.ydhl.micro.api.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName PageInfoDTO
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 15:44:44
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "返回的带分页信息的实体")
public class PageInfoDTO<T> {

    @ApiModelProperty(value = "返回的数据")
    private List<T> list;

    @ApiModelProperty(value = "每页条数")
    private int pageSize;

    @ApiModelProperty(value = "当前页码")
    private int pageNum;

    @ApiModelProperty(value = "总页数")
    private int totalPage;

    @ApiModelProperty(value = "总记录数")
    private Long total;

}
