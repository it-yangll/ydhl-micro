package com.ydhl.micro.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        //消息转换器，一般情况下可以省略，只需要添加相关依赖即可
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        messageConverters.add(new FormHttpMessageConverter());
//        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
//        messageConverters.add(new MappingJackson2HttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(factory);
//		restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    /**
     * ClientHttpRequestFactory接口的第一种实现方式，即：
     * SimpleClientHttpRequestFactory：底层使用java.net包提供的方式创建Http连接请求
     *
     * @return
     */
    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(15000);

        return requestFactory;
    }

}
