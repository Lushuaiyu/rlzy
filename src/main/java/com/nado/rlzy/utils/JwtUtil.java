package com.nado.rlzy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nado.rlzy.db.pojo.HrUser;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 得到userid
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:53 2019/8/6
     * @Param [request]
     * @return java.lang.String
     **/
    public static String getUserId(){
        HttpServletRequest request = null;
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }
}