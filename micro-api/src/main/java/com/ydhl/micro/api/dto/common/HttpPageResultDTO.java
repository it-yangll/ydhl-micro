package com.ydhl.micro.api.dto.common;

import com.ydhl.micro.api.enumcode.CodeEnumClass;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PageableDTO
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 15:38:13
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "统一返回的带分页信息的实体")
public class HttpPageResultDTO<T> implements Serializable {

    @ApiModelProperty(value = "成功标识")
    private Boolean success;

    @ApiModelProperty(value = "自定义返回状态码;200:成功")
    private String code;

    @ApiModelProperty(value = "自定义返回消息")
    private String message;

    private PageInfoDTO<T> data;

    /**
     * @param list       : 返回集合
     * @param pageSize   : 一页多少条
     * @param pageNum    : 当前页
     * @param totalPages : 总页数
     * @param total      : 总记录数
     * @return com.ydhl.micro.api.dto.common.PageableDTO :
     * @Description //TODO
     * @Author yangll
     * @Date 2019-9-10 15:44:06
     **/
    public static HttpPageResultDTO ok(List list, Integer pageSize, Integer pageNum, Integer totalPages, Long total) {
        HttpPageResultDTO result = new HttpPageResultDTO();
        result.setSuccess(true);
        result.setCode(GlobalCodeEnum.OK.getCode());
        result.setMessage(GlobalCodeEnum.OK.getMsg());

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPages);
        data.put("total", total);
        data.put("pageNum", pageNum);
        data.put("list", list);

        PageInfoDTO pi = new PageInfoDTO();
        pi.setList(list);
        pi.setPageSize(pageSize);
        pi.setPageNum(pageNum);
        pi.setTotalPage(totalPages);
        pi.setTotal(total);
        result.setData(pi);

        return result;
    }

    public static HttpPageResultDTO fail(CodeEnumClass codeEnum) {
        HttpPageResultDTO result = new HttpPageResultDTO();
        result.setSuccess(false);
        result.setCode(codeEnum.getEnumCode());
        result.setMessage(codeEnum.getEnumMsg());
        return result;
    }

    public static HttpPageResultDTO fail(CodeEnumClass codeEnum, String errorMessage) {
        HttpPageResultDTO result = new HttpPageResultDTO();
        result.setSuccess(false);
        result.setCode(codeEnum.getEnumCode());
        result.setMessage(errorMessage);
        return result;
    }


}
