package com.ydhl.micro.api.dto.admin.sys.resource;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName SearchDemoDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/19 14:02
 * @Version 1.0
 **/
@Data
@ToString
public class ResourceTreeDTO {

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

    private List<ResourceTreeDTO> children;

}
