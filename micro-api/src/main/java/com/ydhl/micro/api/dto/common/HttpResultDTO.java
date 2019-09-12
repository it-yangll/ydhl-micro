package com.ydhl.micro.api.dto.common;

import com.ydhl.micro.api.enumcode.CodeEnumClass;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ClassName ResponseDTO
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 15:44:30
 * @Version 1.0
 **/
@Data
@ToString
@ApiModel(value = "统一返回的实体")
public class HttpResultDTO<T> implements Serializable {

    @ApiModelProperty(value = "成功标识")
    private Boolean success;

    @ApiModelProperty(value = "自定义返回状态码;200:成功")
    private String code;

    @ApiModelProperty(value = "自定义返回消息")
    private String message;

    @ApiModelProperty(value = "数据信息")
    private T data;

    public static HttpResultDTO ok() {
        HttpResultDTO result = new HttpResultDTO();
        result.setSuccess(true);
        result.setCode(GlobalCodeEnum.OK.getCode());
        result.setMessage(GlobalCodeEnum.OK.getMsg());
        return result;
    }

    public static HttpResultDTO ok(Object data) {
        HttpResultDTO result = new HttpResultDTO();
        result.setSuccess(true);
        result.setCode(GlobalCodeEnum.OK.getCode());
        result.setMessage(GlobalCodeEnum.OK.getMsg());
        result.setData(data);
        return result;
    }

    public static HttpResultDTO fail(CodeEnumClass codeEnum) {
        HttpResultDTO result = new HttpResultDTO();
        result.setSuccess(false);
        result.setCode(codeEnum.getEnumCode());
        result.setMessage(codeEnum.getEnumMsg());
        return result;
    }

    public static HttpResultDTO fail(CodeEnumClass codeEnum, String errorMessage) {
        HttpResultDTO result = new HttpResultDTO();
        result.setSuccess(false);
        result.setCode(codeEnum.getEnumCode());
        result.setMessage(errorMessage);
        return result;
    }

    public static HttpResultDTO error(String message) {
        HttpResultDTO result = new HttpResultDTO();
        result.setSuccess(false);
        result.setCode(GlobalCodeEnum.ERR_PARAM_BIND.getCode());
        if (StringUtils.isNotBlank(message)) {
            result.setMessage(message);
        } else {
            result.setMessage(GlobalCodeEnum.ERR_PARAM_BIND.getMsg());
        }
        result.setData(null);
        return result;
    }

}
