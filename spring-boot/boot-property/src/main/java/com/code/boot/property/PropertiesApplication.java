package com.code.boot.property;
import com.code.boot.property.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 可以使用 @EnableConfigurationProperties注解激活指定属性绑定类（加了@ConfigurationProperties注解的类），把它交给Spring管理。
 * 一般常使用@Component注解把该绑定类交给Spring容器管理更常用些，不过在自动配置类编写时@EnableConfigurationProperties注解使用多些。
 *
 * @author shunhua
 * @date 2019-10-26
 */
@EnableAutoConfiguration
public class PropertiesApplication {

    /**
     *  @ConditionalOnProperty指定的条件满足时才会装配UserBean，否则不会装配User Bean
     *    - name/value：指定属性名
     *    - matchIfMissing: 没有指定的属性是否继续装配
     *    - havingValue: 指定属性值
     *    - prefix ： 指定属性的前缀,用于配合name/value，拼接成完整的属性名
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "user")
    @ConditionalOnProperty(name="city.name",matchIfMissing = false,prefix = "user",havingValue = "China")
    public User user() {
        return new User();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(PropertiesApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 从Spring中取出User实例
        User user = context.getBean(User.class);
        System.out.println(user);

        // 关闭容器
        context.close();

    }


}