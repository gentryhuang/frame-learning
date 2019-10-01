package com.code.boot.mybatis.dao;

import com.code.boot.mybatis.BaseTest;
import com.code.boot.mybatis.dao.AccountMapper;
import com.code.boot.mybatis.model.AccountDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AccountMapperTest
 *
 * @author shunhua
 * @date 2019-10-01
 */
public class AccountMapperTest extends BaseTest {
    @Autowired
    private AccountMapper accountMapper;

    // ------------------------------------ 使用配置文件的方式 ---------------------------------------//
    @Test
    public void testAddAccount() {
        AccountDO accountDO = new AccountDO();
        accountDO.setName("gentryhuang");
        accountDO.setPassword("123456");
        int count = accountMapper.insertSelective(accountDO);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testUpdateAccount() {
        AccountDO accountDO = accountMapper.get(1);
        accountDO.setPassword("111111");
        int count = accountMapper.update(accountDO);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testDeleteAccount() {
        int count = accountMapper.delete(2);
        Assert.assertEquals(1, count);
    }

    // -------------------------使用注解方式 --------------------------//

    @Test
    public void testFindById(){
        AccountDO accountDO = accountMapper.findById(1);
        Assert.assertNotNull(accountDO);
    }

    @Test
    public void testFindAll(){
        List<AccountDO> accountDOList = accountMapper.findAll();
        Assert.assertEquals(1,accountDOList.size());
    }

}
