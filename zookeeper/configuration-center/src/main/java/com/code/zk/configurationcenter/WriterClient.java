package com.code.zk.configurationcenter;

import java.util.Properties;

/**
 * WriterClient
 *
 * @author shunhua
 * @date 2019-10-27
 */
public interface WriterClient {

    /**
     * 向配置中心写入配置
     * @param fileName 文件名
     * @param file 文件的数据
     */
    void create(String fileName, Properties file) throws Exception;

    /**
     * 修改配置中心的配置
     * @param fileName 文件名
     * @param file 文件中的数据
     */
    void modify(String fileName,Properties file) throws Exception;

    /**
     * 删除配置中心的配置
     * @param fileName 文件名
     */
    void delete(String fileName);

    /**
     * 获取配置中心的配置
     * @param fileName
     * @return
     */
    Properties load(String fileName);

}