package com.code.zk.configurationcenter;

import com.alibaba.common.convert.Convert;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.*;
import java.util.concurrent.*;

/**
 * 配置中心，与读写操作交互
 *
 * @author shunhua
 * @date 2019-10-27
 */
public class ZkCenter implements ReaderClient, WriterClient {

    /**
     * 命名空间
     */
    private final String NAME_SPACE = "/zk_reg";

    /**
     * 分隔符号
     */
    private final String SEPARATOR_LINE = "/";

    /**
     * ZkClient
     */
    private ZkClient zkClient;

    /**
     * 配置中心初始化
     *
     * @param address
     */
    public ZkCenter(String address) {
        zkClient = new ZkClient(address);
        if (!zkClient.exists(NAME_SPACE)) {
            zkClient.createPersistent(NAME_SPACE);
        }
    }

    /**
     * 写入配置数据
     *
     * @param fileName 文件名
     * @param file     文件的数据
     * @throws Exception
     */
    @Override
    public void create(String fileName, Properties file) throws Exception {
        // 拼接配置文件全路径名
        String fileNamePath = NAME_SPACE + SEPARATOR_LINE + fileName;
        if (zkClient.exists(fileNamePath)) {
            throw new Exception(String.format("%s 节点已经存在", fileNamePath));
        }
        // 创建配置文件名对应的节点
        zkClient.createPersistent(fileNamePath);

        // 创建配置文件中的具体配置项节点以及对应的值
        Set<Object> keys = file.keySet();
        for (Object key : keys) {
            String fileItem = fileNamePath + SEPARATOR_LINE + Convert.asString(key);
            String fileItemValue = Convert.asString(file.get(key));
            zkClient.createPersistent(fileItem, fileItemValue);
        }

    }

    /**
     * 这里是对配置中心的配置文件进行删除操作，采用先删除再创建
     *
     * @param fileName 文件名
     * @param file     文件中的数据
     * @throws Exception
     */
    @Override
    public void modify(String fileName, Properties file) throws Exception {
        this.delete(fileName);
        this.create(fileName, file);
    }

    /**
     * 删除配置文件以及配置文件下的数据
     *
     * @param fileName 文件名
     */
    @Override
    public void delete(String fileName) {

        fileName = NAME_SPACE + SEPARATOR_LINE + fileName;

        if (!zkClient.exists(fileName)) {
            return;
        }
        // 进行递归删除
        zkClient.deleteRecursive(fileName);
    }

    /**
     * 读取配置文件上的配置数据，以集合形式返回
     *
     * @param fileName
     * @return
     */
    @Override
    public Properties load(String fileName) {

        Properties properties = new Properties();

        String rootPath = NAME_SPACE + SEPARATOR_LINE + fileName;

        String fileNamePath = NAME_SPACE + SEPARATOR_LINE + fileName;
        // 获取配置文件名下的配置项路径（这里只有直接一层，不考虑多层）
        List<String> childrenPath = zkClient.getChildren(fileNamePath);
        childrenPath.forEach(path -> {
            String itemPath = rootPath + SEPARATOR_LINE + path;
            Object value = zkClient.readData(path);
            properties.put(path, value);
        });
        return properties;
    }

    @Override
    public void watch(String fileName, ChangeEvent changeEvent) {

        // 创建一个定时器
        ScheduledThreadPoolExecutor scheduled = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        // 设置 从线程池中移出已经取消的任务策略
        scheduled.setRemoveOnCancelPolicy(true);

        // 监听配置文件节点下的配置项
        String fileNamePath = NAME_SPACE + SEPARATOR_LINE + fileName;

        CopyOnWriteArrayList<ScheduledFuture> totalList = new CopyOnWriteArrayList<>();


        // 监控文件名节点下的配置项节点变化
        zkClient.subscribeChildChanges(fileNamePath, new IZkChildListener() {
            /**
             * 对应修改操作是先删除后创建，我们监控的正是子节点的变化，因此只需通知一次即可，要么是删除引起的要么是创建引起的。
             * 因此要过滤。
             * @param s
             * @param list
             * @throws Exception
             */
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {

                /**
                 * 5秒内只处理最后一次
                 */
                totalList.forEach(scheduledFuture -> {
                    if (scheduledFuture != null && !scheduledFuture.isCancelled() && !scheduledFuture.isDone()) {
                        scheduledFuture.cancel(true);
                    }
                });

                ScheduledFuture future = scheduled.schedule(() -> {
                    Properties properties = load(fileName);
                    changeEvent.dataChange(properties);

                }, 5, TimeUnit.SECONDS);
                // 加入
                totalList.add(future);
            }
        });

    }
}