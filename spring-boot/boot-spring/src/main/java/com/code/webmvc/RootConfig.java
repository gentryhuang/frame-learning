package com.code.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * RootConfig
 *
 * @author shunhua
 * @date 2019-10-19
 */
@Configuration
@ComponentScan(basePackages = "com.code.webmvc.business",
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)
})
public class RootConfig {

}