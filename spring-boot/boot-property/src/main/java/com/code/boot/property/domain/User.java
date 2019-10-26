package com.code.boot.property.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 注意：
 *  使用ConfigurationProperties进行属性绑定时，必须实现对象中属性的setter方法，否则无法完成绑定。即使是嵌套绑定，也是如此递归模式
 *
 * @author shunhua
 * @date 2019-10-26
 */
//@ConfigurationProperties(prefix = "user")
public class User {

    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 所在城市
     */
    private City city;

    public static class City{

        /**
         * 城市邮编
         */
        private String postCode;

        /**
         * 名称
         */
        private String name;

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "City{" +
                    "postCode='" + postCode + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}