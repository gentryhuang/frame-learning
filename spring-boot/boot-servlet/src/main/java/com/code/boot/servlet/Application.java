package com.code.boot.servlet;

import com.code.boot.servlet.bean.AsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Application
 *
 * @author shunhua
 * @date 2019-10-25
 */
@EnableAutoConfiguration
//@ServletComponentScan(basePackages = {"com.code.boot.servlet.bean"})  // 荣获@ServletComponentScan 注解扫描组件
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    /**
     * 通过XxxRegistrationBean为容器中加入Servlet组件，这里以Servlet为例
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletRegistrationBean asyncServletServletRegistrationBean(){
        ServletRegistrationBean registrationBean =  new ServletRegistrationBean(new AsyncServlet(),"/");
        registrationBean.setName("MyAsyncServlet");
        return registrationBean;
    }

    /**
     * 通过ServletContextInitializer的方式给容器中加入Servlet组件，这里以Filter为例
     * @return
     */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }

    /***
     * 也可以通过继承WebMvcConfigurer
     */


}