package com.code.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * WebConfig
 *
 * 1 由于使用了@EnableWebMvc会导入处理器映射器以及处理器适配器。如果不显示配置视图解析器，默认会使用BeanNameViewResolver。一般我们会显示配置视图解析器
 * 2 这里使用了@ComponentScan注解，启动组件扫描，一般扫描控制器
 * 3 注意在Spring5的时候淘汰了WebMvcConfigurerAdater，而是支持{@WebMvcConfigurer}。这里引入的Spring4
 *
 * @author shunhua
 * @date 2019-10-19
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.code.webmvc.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     *  这种方式也可以，覆盖WebMVcConfigurer中的方法也可以。这种方式适合WebMvcConfigurerAdapter类，不过Spring5已经把它淘汰了
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    /**
     * 配置静态资源的处理，交给Servlet容器中默认的Servlet处理,而不是DispatcherServlet
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}