package com.ydhl.micro.api.dto.admin.sys.role;

import com.ydhl.micro.api.enumcode.consts.ResourceType;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @ClassName NavTreeDTO
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 12:41
 * @Version 1.0
 **/
@Data
@ToString
public class RoleResourceTreeDTO {

    private Long id;

    private Long parentId;

    private String parentPath;

    private String name;

    private String permission;

    private String remark;

    private String resourceType;

    private String resourceUrl;

    private String menuClass;

    private Integer sort;

    private Boolean available;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

    private Boolean check = false;

    private List<RoleResourceTreeDTO> children;

    private String availableName;

    private String resourceTypeName;

    public String getAvailableName() {
        return this.available ? "正常" : "禁用";
    }

    public String getResourceTypeName() {
        return ResourceType.valueOf(this.resourceType).getShowText();
    }

}
