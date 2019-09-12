package com.ydhl.micro.api.dto.admin.sys.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName DemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 13:48
 * @Version 1.0
 **/
@Data
@ToString
public class ModifyDicitemDTO {

    @NotNull(message = "ID为空")
    private Long id;

    @NotBlank(message = "值为空")
    @Length(max = 255, message = "值请控制在255个字符以内")
    private String itemValue;

    @Length(max = 500, message = "备注请控制在500个字符以内")
    private String remark;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;


}
