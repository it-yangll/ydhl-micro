package com.ydhl.micro.api.dto.admin.sys;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/28 12:46
 * @Version 1.0
 **/
@Data
@ToString
public class Menu {

    private Long id;

    private String name;

    private String resourceType;

    private String resourceUrl;

    private String menuClass;

}
