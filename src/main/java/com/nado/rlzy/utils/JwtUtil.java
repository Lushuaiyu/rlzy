package com.nado.rlzy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nado.rlzy.db.pojo.HrUser;

/**
 * @ClassName jwt token相关
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/26 10:26
 * @Version 1.0
 */
public class JwtUtil {
    public String getToken(HrUser user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}