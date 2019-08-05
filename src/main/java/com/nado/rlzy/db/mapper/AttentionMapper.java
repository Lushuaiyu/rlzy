package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Attention;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lushuaiyu
 */
public interface AttentionMapper extends Mapper<Attention> {

    /**
     * 招聘端和求职端 聊天里的取消关注
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:19 2019/7/31
     * @Param [query]
     * @return int
     **/
    int updateAttention(Attention query);
}