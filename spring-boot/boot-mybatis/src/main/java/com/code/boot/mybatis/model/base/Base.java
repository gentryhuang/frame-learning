package com.code.boot.mybatis.model.base;

import java.io.Serializable;

/**
 * Base
 *
 * @author shunhua
 * @date 2019-10-01
 */
public class Base implements Serializable {
    /**
     * 是否有效
     */
    private Integer isValid = 1;

    /**
     * 版本号
     */
    private Integer lastVer = 1;

    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * 更新时间
     */
    private Long opTime = System.currentTimeMillis();
}