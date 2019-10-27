package com.code.zk.configurationcenter;

import java.util.Properties;

/**
 * ReaderClient
 *
 * @author shunhua
 * @date 2019-10-27
 */
public interface ReaderClient {

    /**
     * 获取配置中心的配置
     * @param fileName
     * @return
     */
    Properties load(String fileName);

    /**
     * 监听配置中心的配置文件
     * @param fileName 文件名
     * @param changeEvent  配置中心的配置文件发生时的回调接口
     */
    void watch(String fileName,ChangeEvent changeEvent);

    /**
     * 监听回调接口
     */
    @FunctionalInterface
    interface ChangeEvent{

        /**
         * 监听回调方法
         * @param properties  发生变化后配置中心的数据
         */
        void dataChange(Properties properties);
    }

}
