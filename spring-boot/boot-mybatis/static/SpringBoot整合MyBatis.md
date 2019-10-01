
# SpringBoot整合MyBatis

### 方式一

- 在配置文件中指定mybatis的映射文件位置，也可以选择指定其他的配置

   ![](mb-1.png)

- 在Mapper接口上加上@Mapper注解

   ![](mb-2.png)
 
```text
注意：加上这个注解的作用是，在扫描到它时会结合mybatis映射文件，Spring会为这个接口创建一个代理对象。一般把这些注解放到SpringBoot主程序类所在包或者子包下，这样可以直接扫描到了。
```

- 数据库信息以及数据源需要提前配置，当然不明确指定数据源SpringBoot会自动为我们创建默认提供的

- 使用SpringBoot主程序入口类上的注解 *@SpringBootApplication*默认规则扫描包、自动装配组件以及启动容器

   ![](mb-3.png)


###　方式二

- 在配置文件中指定mybatis的映射文件位置，也可以选择指定其他的配置
 
   ![](mb-1.png)

- 使用@MapperScan注解扫描指定包下的Mapper接口

　 ![](mb-4.png)

```text
注意：
 - 加上这个注解的作用是，在应用启动时会扫描指定路径下的Mapper接口，结合mybatis映射文件，Spring会为这个接口创建一个代理对象。一般把这些注解放到SpringBoot主程序类上，当然也可以放在其他的配置类上。
 - 这种情况Mapper接口什么都不需要加，@MapperScan扫描到的接口会自动结合配置文件为它创建代理对象
``` 
- 数据库信息以及数据源需要提前指定，当然不明确指定数据源SpringBoot会自动为我们创建

- 使用SpringBoot主程序入口类上的注解 *@SpringBootApplication*默认规则扫描包、自动装配组件以及启动容器

### 方法三

- 使用@MapperScan注解扫描指定包下的Mapper接口，再使用@ImportResource注解导入mybatis的映射文件

    ![](mb-5.png)


```text
注意：
 - 加上这个注解的作用是，在应用启动时会扫描指定路径下的Mapper接口，结合mybatis映射文件，Spring会为这个接口创建一个代理对象。一般把这些注解放到SpringBoot主程序类上，当然也可以放在其他的配置类上。
 - Mapper接口什么都不需要加，@MapperScan扫描到的接口会自动结合配置文件为它创建代理对象
```
- 数据库信息以及数据源需要提前指定，当然不明确指定数据源SpringBoot会自动为我们创建

- 使用SpringBoot主程序入口类上的注解 *@SpringBootApplication*默认规则扫描包、自动装配组件以及启动容器


> 注意：<br>
采用这种方式的话，如果使用基本的功能就用不到配置文件了，但是如果想使用MyBatis的其他功能就需要到配置文件中配置了。

#### 综上
 
推荐使用方式一
