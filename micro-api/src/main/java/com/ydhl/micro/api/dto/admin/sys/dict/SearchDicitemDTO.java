package com.ydhl.micro.api.dto.admin.sys.dict;

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
@ApiModel(value = "数据字典查询字段")
public class SearchDicitemDTO extends SearchDTO {
    @ApiModelProperty(value = "字典类型代码", example = "engine")
    private String typeCode;

    @ApiModelProperty(value = "配置项代码", example = "engine1")
    private String itemCode;

    @ApiModelProperty(value = "配置项值", example = "国产T100型")
    private String itemValue;

}
