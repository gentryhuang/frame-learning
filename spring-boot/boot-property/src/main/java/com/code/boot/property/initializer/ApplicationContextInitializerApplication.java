package com.code.boot.property.initializer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * ApplicationContextInitializerApplication
 *
 * @author shunhua
 * @date 2019-10-26
 */
@EnableAutoConfiguration
public class ApplicationContextInitializerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ApplicationContextInitializerApplication.class)
                        .web(WebApplicationType.NONE)
                        // 设置属性
                        .properties("myproperty=10")
                        .run(args);

        ConfigurableEnvironment environment = context.getEnvironment();

        /**
         * 打印当前环境中的PropertySource
         */
        environment.getPropertySources().forEach(propertySource ->{
            System.out.printf("PropertySource名称：%s : %s\n",propertySource.getName(),propertySource);
        });

    }


}