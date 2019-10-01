package com.code.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Application
 *
 * @author shunhua
 * @date 2019-10-01
 */
@SpringBootApplication
@MapperScan({"com.code.boot.mybatis.v1.dao"})
@ImportResource({"classpath*:/mapper/**/*Mapper.xml"})
public class Application {
   public static void main(String[] args){
       SpringApplication.run(Application.class,args);
   }
}

