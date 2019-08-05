package com.nado.rlzy.service;

import com.nado.rlzy.bean.query.AttentionQuery;

/**
 * @ClassName 关注 service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 10:37
 * @Version 1.0
 */
public interface ChatService {
    /**
     * 求职端 关注 简章表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:51 2019/7/31
     * @Param [query]
     * @return int
     **/
    int attention(AttentionQuery query);

    /**
     * 招聘端和求职端 聊天里的取消关注
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:35 2019/7/31
     * @Param [query]
     * @return int
     **/
    int updateAttention(AttentionQuery query);
}