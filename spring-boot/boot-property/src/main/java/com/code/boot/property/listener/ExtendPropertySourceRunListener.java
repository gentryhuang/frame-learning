package com.code.boot.property.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link org.springframework.core.env.PropertySources}
 *
 * @author shunhua
 * @date 2019-10-26
 */
public class ExtendPropertySourceRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;

    private final String[] args;

    public ExtendPropertySourceRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> source = new HashMap<>(2);

        source.put("my_key", "hello world");

        MapPropertySource mapPropertySource = new MapPropertySource("myName", source);

        propertySources.addFirst(mapPropertySource);

    }

    @Override
    public void starting() {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

        ConfigurableEnvironment environment = context.getEnvironment();

        /**
         * 打印当前环境中的PropertySource
         */
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertySource名称：%s : %s\n", propertySource.getName(), propertySource);
        });
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }


}