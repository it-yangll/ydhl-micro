package com.ydhl.micro.api.dto.admin.sys.resource;

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
public class ResponseResourceDTO {

    private Long id;

    private Long parentId;

    private String parentPath;

    private String name;

    private String permission;

    private String remark;

    private String resourceTypeName;

    private String resourceType;

    private String resourceUrl;

    private String menuClass;

    private Integer sort;

    private String availableName;

    private Boolean available;

}
