package com.ydhl.micro.api.dto.admin.sys.post;

import com.ydhl.micro.api.dto.common.SearchDTO;
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
public class SearchPostDTO extends SearchDTO {
    private String name;
    private String code;
}
