package com.code.boot.mybatis.model;

import com.code.boot.mybatis.model.base.Base;
import lombok.Data;

/**
 * AccountDO
 *
 * @author shunhua
 * @date 2019-10-01
 */
@Data
public class AccountDO extends Base {

    private static final long serialVersionUID = 4817626289796308594L;
    /**
     * id
     */
    private Integer id;
    /**
     * 账号名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
}