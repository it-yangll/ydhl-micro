package com.ydhl.micro.api.dto.admin.sys.dict;

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
public class CreateDicitemDTO {

    @NotBlank(message = "类型为空")
    private String typeCode;

    @NotBlank(message = "编码为空")
    @Length(max = 255, message = "编码请控制在255个字符以内")
    private String itemCode;

    @NotBlank(message = "值为空")
    @Length(max = 255, message = "值请控制在255个字符以内")
    private String itemValue;

    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
