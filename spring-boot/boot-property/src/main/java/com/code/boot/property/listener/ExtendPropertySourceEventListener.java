package com.code.boot.property.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link org.springframework.core.env.PropertySources}实现，监听{@link ApplicationEnvironmentPreparedEvent}
 *
 * @author shunhua
 * @date 2019-10-26
 */
public class ExtendPropertySourceEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        // 为环境添加相关配置
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(2);
        source.put("onApplicationEvent_key", "onApplicationEvent");
        MapPropertySource mapPropertySource = new MapPropertySource("onApplicationEvent", source);
        propertySources.addFirst(mapPropertySource);

        /**
         * 打印当前环境中的PropertySource
         */
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertySource名称：%s : %s\n", propertySource.getName(), propertySource);
        });
    }
}