package com.ydhl.micro.api.dto.ydhlCloud;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @Description
 * @Author: yangll
 * @Date: 2019-9-11 11:53:34
 * @Version 1.0
 */
@Data
@ToString
public class HeliCloudRequestDTO {

    @JsonProperty("RfcName")
    private String rfcName;

    @JsonProperty("InField")
    private JSONObject inField;

    @JsonProperty("InTable")
    private JSONObject inTable;

    @JsonProperty("OutField")
    private Object outField;

    @JsonProperty("OutTable")
    private Object outTable;

}
