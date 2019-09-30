package com.code.boot.mybatis.v1.dao;

import com.code.boot.mybatis.v1.model.AccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AccountMapper 动态Mapper
 *
 * @author shunhua
 * @date 2019-10-01
 */
@Mapper
public interface AccountMapper {
    /**
     * 新增
     *
     * @param accountDO
     * @return
     */
    int insert(AccountDO accountDO);

    /**
     * 根据 PK 查询
     * @param id
     * @return
     */
    AccountDO get(int id);

    /**
     * 修改
     * @param accountDO
     * @return
     */
    int update(AccountDO accountDO);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(int id);
}
