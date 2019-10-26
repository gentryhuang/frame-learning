package com.code.boot.property.processor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link org.springframework.core.env.PropertySources} 实现
 *
 * @author shunhua
 * @date 2019-10-26
 */
public class ExtendPropertySourcesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String ,Object> source = new HashMap<>(2);

        source.put("postProcessEnvironment_key","postProcessEnvironment");

        MapPropertySource mapPropertySource = new MapPropertySource("postProcessEnvironment",source);

        propertySources.addFirst(mapPropertySource);

        /**
         * 打印当前环境中的PropertySource
         */
        environment.getPropertySources().forEach(propertySource ->{
            System.out.printf("PropertySource名称：%s : %s\n",propertySource.getName(),propertySource);
        });
    }
}