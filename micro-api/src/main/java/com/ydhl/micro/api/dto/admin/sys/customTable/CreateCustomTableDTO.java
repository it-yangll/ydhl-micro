package com.ydhl.micro.api.dto.admin.sys.customTable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName DemoDTO
 * @Description 新增
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
public class CreateCustomTableDTO {

    @NotBlank(message = "表格标识符为空")
    @Length(max = 50, message = "表格标识符请控制在50个字符以内")
    private String tableId;

    @Length(max = 1000, message = "显示列过多")
    @ApiModelProperty(value = "展示表格列字符串", example = "ia,name,mobil,addr,username,createId,dateTime")
    private String showColumns;

}
