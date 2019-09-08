package com.nado.rlzy.service;

import com.nado.rlzy.bean.query.AttentionQuery;

import java.util.Map;

/**
 * @ClassName 消息 service
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

    /**
     * 本人通知 代招单位 && 招聘单位
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 14:20 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */
    Map<String, Object> queryMessageMyself(Integer userId);

    /**
     * 推荐人通知 代招单位 && 招聘单位
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 15:44 2019-09-07
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> queryMessageReferrer(Integer userId);
}