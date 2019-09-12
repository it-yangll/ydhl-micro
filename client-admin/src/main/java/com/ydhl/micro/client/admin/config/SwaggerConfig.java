package com.ydhl.micro.client.admin.config;

import com.ydhl.micro.api.enumcode.consts.LoginType;
import com.ydhl.micro.api.enumcode.consts.UserType;
import com.ydhl.micro.core.security.JwtBody;
import com.ydhl.micro.core.security.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName SwaggerConfig
 * @Description swagger配置类
 * @Author yangll
 * @Date 2019-9-10 15:50:40
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        JwtBody jwtBody = JwtBody.createJwtBody(UUID.randomUUID().toString(), 864000000, "127.0.0.1", LoginType.ADMIN, UserType.ADMIN, "admin", 1L, "管理员");

        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar
                .name(JwtUtil.JWT_NAME)
                .description("AccessToken令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header")

                .defaultValue(JwtUtil.buildJWT(jwtBody))
                .required(true)
                .build();

        List<Parameter> pars = new ArrayList<Parameter>();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("YDHL开发平台接口文档")
                .description("后台运营管理系统相关接口的文档")
                .termsOfServiceUrl("https://blog.csdn.net/weixin_42156742/article/details/81703867")
                .version("2.0")
                .build();
    }

}
