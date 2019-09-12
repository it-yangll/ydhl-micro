package com.ydhl.micro.api.dto.admin.sys;

import lombok.Data;
import lombok.ToString;

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
public class NavTreeDTO {

    private Menu menu;

    private List<NavTreeDTO> childs;

}
