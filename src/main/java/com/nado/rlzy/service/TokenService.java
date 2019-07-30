package com.nado.rlzy.service;

import com.nado.rlzy.db.pojo.HrUser;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/26 11:25
 * @Version 1.0
 */
public interface TokenService {
    public String getToken(HrUser user);
}