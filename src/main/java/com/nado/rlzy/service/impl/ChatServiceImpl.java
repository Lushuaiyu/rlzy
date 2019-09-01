package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.query.AttentionQuery;
import com.nado.rlzy.db.mapper.AttentionMapper;
import com.nado.rlzy.db.pojo.Attention;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @ClassName 关注实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 10:38
 * @Version 1.0
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private AttentionMapper attentionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAttention(AttentionQuery query) {
        Attention attention = new Attention();
        attention.setUseredid(query.getUseredId());
        attention.setUseringid(query.getUserId());
        attention.setModifyTime(LocalDateTime.now());
        return attentionMapper.updateAttention(attention);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int attention(AttentionQuery query) {
        Attention attention = new Attention();
        attention.setUseringid(query.getUserId());
        attention.setUseredid(query.getUseredId());
        attention.setObjectid(query.getObjectId());
        if (query.getType().equals(1)) {
            attention.setType(1);
        } else if (query.getType().equals(0)) {
            attention.setType(0);
        } else {
            Assert.isFalse(query.getType() != 0 || query.getType() != 1, RlzyConstant.OPS_FAILED_MSG);
        }
        attention.setCreatetime(LocalDateTime.now());
        return attentionMapper.insertSelective(attention);
    }
}