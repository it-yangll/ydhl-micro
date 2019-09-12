package com.ydhl.micro.gateway.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConfigProperties
 * @Description TODO
 * @Author yangll
 * @Date 2019/3/27 11:26
 * @Version 1.0
 **/
@Component
@Configuration
@PropertySource(value = "classpath:config.properties")
@ConfigurationProperties(prefix = "white")
@Data
@ToString
public class ConfigProperties {

    private List<String> list = new ArrayList<>();
    private List<String> nonLog = new ArrayList<>();

}
