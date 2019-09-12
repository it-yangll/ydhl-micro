package com.ydhl.micro;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.ydhl.micro.base.listener.ApplicationStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@MapperScan({"com.ydhl.micro.**.dao.auto", "com.ydhl.micro.**.dao.manual"})
public class MicroBaseApplication {

    @Value("${welcome}")
    private String welcome;

    @RequestMapping("/")
    public String home() {
        return welcome;
    }

    @GetMapping("/druid/stat")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication springApplication = new SpringApplication(MicroBaseApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }

}
