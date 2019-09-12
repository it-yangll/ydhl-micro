package com.ydhl.micro.api.dto.admin.sys.role;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName SearchDemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 14:02
 * @Version 1.0
 **/
@Data
@ToString
public class ResponseRoleDTO {

    private Long id;

    private String name;

    private String role;

    private String remark;

    private String presetName;

    private Boolean preset;

    private String stateName;

    private Boolean available;

}
