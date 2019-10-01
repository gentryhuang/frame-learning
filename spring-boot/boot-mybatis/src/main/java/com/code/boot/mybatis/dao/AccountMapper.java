package com.code.boot.mybatis.dao;

import com.code.boot.mybatis.model.AccountDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    int insertSelective(AccountDO accountDO);
    /**
     * 根据 PK 查询
     *
     * @param id
     * @return
     */
    AccountDO get(int id);
    /**
     * 修改
     *
     * @param accountDO
     * @return
     */
    int update(AccountDO accountDO);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据PK查询
     */
    @Select("SELECT * FROM  account WHERE id = #{id}")
    AccountDO findById(int id);
    /**
     * 查询所有账号
     *
     * @return
     */
    @Select("SELECT * FROM  account ")
    List<AccountDO> findAll();
}
