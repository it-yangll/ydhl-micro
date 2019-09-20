package com.ydhl.micro.base.weixin.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ResponseWxSessionDTO
 * @Description 返回微信登录凭证信息
 * @Author Ly
 * @Date 2019/6/15 12:08
 * @Version 1.0
 **/
@ApiModel(value = "微信登录凭证信息")
@Data
@ToString
public class ResponseWxSessionDTO {

    /** 用户唯一标识 */
    @ApiModelProperty(value = "openid")
    private String openid;

    /** 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。 */
    @ApiModelProperty(value = "unionid")
    private String unionid;

    public static ResponseWxSessionDTO transform(WxCode2SessionDTO wxCode2SessionDTO) {
        ResponseWxSessionDTO result = new ResponseWxSessionDTO();
        BeanUtil.copyProperties(wxCode2SessionDTO, result);
        return result;
    }

}
