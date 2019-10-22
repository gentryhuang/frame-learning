package com.code.webmvc;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebAppInitializer
 *
 * 借助于Servlet3规范和Spring3.1的功能，替换web.xml的方式
 *
 * @author shunhua
 * @date 2019-10-19
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 返回的配置类将会用来配置ContextLoaderListener创建的应用上下文(Spring上下文)中的Bean
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 返回的配置类将会用来定义DispatcherServlet应用上下文(SpringMVC上下文)中的Bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 配置将DispatcherServlet映射到的路径,这里处理进入应用的所有请求
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}