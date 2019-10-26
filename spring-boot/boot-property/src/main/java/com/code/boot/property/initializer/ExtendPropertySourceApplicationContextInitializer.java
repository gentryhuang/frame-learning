package com.code.boot.property.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * ExtendPropertySourceApplicationContextInitializer
 *
 * @author shunhua
 * @date 2019-10-26
 */
public class ExtendPropertySourceApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        // TODO 为环境添加相关配置

        /**
         * 打印当前环境中的PropertySource
         */
        environment.getPropertySources().forEach(propertySource ->{
            System.out.printf("PropertySource名称：%s : %s\n",propertySource.getName(),propertySource);
        });
    }
}