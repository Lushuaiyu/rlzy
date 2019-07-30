package com.nado.rlzy.service;

import com.alipay.api.domain.Account;
import com.nado.rlzy.db.pojo.TestAccount;

/**
 * @ClassName 模拟转账service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/5 10:49
 * @Version 1.0
 */
public interface TestAccountService {
    public TestAccount getAccount( );

    public void addMoney() throws Exception;
}