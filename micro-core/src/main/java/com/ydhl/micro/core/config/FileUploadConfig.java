package com.ydhl.micro.core.config;

import com.ydhl.micro.core.consts.Constants;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName FileUploadConfig
 * @Description TODO
 * @Author Ly
 * @Date 2019/6/11 14:43
 * @Version 1.0
 **/
@Configuration
public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.of(Constants.MAX_FILE_SIZE, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.of(Constants.MAX_REQUEST_SIZE, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

}
