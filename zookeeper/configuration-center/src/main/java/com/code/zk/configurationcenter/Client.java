package com.code.zk.configurationcenter;

import java.util.Properties;

/**
 * Client
 *
 * @author shunhua
 * @date 2019-10-27
 */
public class Client {

    public static void main(String[] args) throws Exception {

        WriterClient writerClient = new ZkCenter("localhost:2181");

        String fileName = "dq-delay";
        Properties prop = new Properties();
        prop.put("shop.log.switch", "true");
        prop.put("add.job", "true");
        prop.put("max.job.num", 200);

        // 往配置中心 上传配置文件
        System.out.println("往配置中心 上传配置文件");
        writerClient.create(fileName, prop);


        new Thread(() -> {
            while (true) {
                Properties file = writerClient.load(fileName);
                // 修改
                file = writerClient.load(fileName);
                file.remove("shop.log.switch");
                file.put("min.delay", 60);
                System.out.println("修改配置中心中的配置文件");
                try {
                    writerClient.modify(fileName, file);
                    Thread.sleep(10000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        ReaderClient readerClient = new ZkCenter("localhost:2181");
        System.out.println(" 监控文件节点下，子节点的变化（创建或删除操作），并打印变化后的注册中心中的配置文件信息");

        new Thread(() ->{
            // 监控文件节点下，子节点的变化（创建或删除操作），并打印变化后的注册中心中的配置文件信息
            readerClient.watch(fileName, System.out::println);
        }).start();



    }

}