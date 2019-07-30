package com.nado.rlzy.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.service.TokenService;
import org.springframework.stereotype.Service;


/**
 * @author jinbin
 * @date 2018-07-08 21:04
 */
@Service("TokenService")
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(HrUser user) {
        String token="";
        // 将 user id 保存到 token 里面
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
