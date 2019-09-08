package com.nado.rlzy.service.impl;

import com.nado.rlzy.bean.frontEnd.MessageFrontEnd;
import com.nado.rlzy.bean.query.AttentionQuery;
import com.nado.rlzy.db.mapper.AttentionMapper;
import com.nado.rlzy.db.mapper.MessageMapper;
import com.nado.rlzy.db.pojo.Attention;
import com.nado.rlzy.db.pojo.Message;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.ChatService;
import com.nado.rlzy.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName 消息实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 10:38
 * @Version 1.0
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private AttentionMapper attentionMapper;

    @Resource
    private MessageMapper messageMapper;


    @Override
    public Map<String, Object> queryMessageReferrer(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<Message> messages = messageMapper.noticeReferrerRepresentativeUnit(userId);
        List<Message> messages1 = messageMapper.noticeReferrerRecruitmentUnit(userId);

        List<Message> collect1 = messages.stream().map(dto -> {
            Integer type = dto.getType();
            if (type.equals(0)) {
                dto.setStringType("平台处理结果通知");
                dto.setType(null);
            } else if (type.equals(1)) {
                dto.setStringType("面试提醒");
                dto.setType(null);
            } else if (type.equals(2)) {
                dto.setStringType("报道提醒");
                dto.setType(null);
            } else if (type.equals(3)) {
                dto.setStringType("72小时内返佣未充值");
                dto.setType(null);
            } else {
                dto.setStringType("给付中断");
                dto.setType(null);
            }
            return dto;
        }).collect(Collectors.toList());


        List<Message> collect = messages1.stream().map(dto -> {

            Integer type = dto.getType();
            if (type.equals(0)) {
                dto.setStringType("平台处理结果通知");
                dto.setType(null);
            } else if (type.equals(1)) {
                dto.setStringType("面试提醒");
                dto.setType(null);
            } else if (type.equals(2)) {
                dto.setStringType("报道提醒");
                dto.setType(null);
            } else if (type.equals(3)) {
                dto.setStringType("72小时内返佣未充值");
                dto.setType(null);
            } else {
                dto.setStringType("给付中断");
                dto.setType(null);
            }
            return dto;
        }).collect(Collectors.toList());
        //代招单位
        map.put("noticeReferrerRepresentativeUnit", collect1);
        map.put("noticeReferrerRecruitmentUnit", collect);
        return map;
    }

    @Override
    public Map<String, Object> queryMessageMyself(Integer userId) {
        Map<String, Object> map = new HashMap<>();

        List<Message> messages = messageMapper.noticeMyselfRepresentativeUnit(userId);
        List<Message> messages1 = messageMapper.noticeMyselfRecruitmentUnit(userId);

        List<Message> collect1 = messages.stream().map(dto -> {

            Integer type = dto.getType();
            if (type.equals(0)) {
                dto.setStringType("平台处理结果通知");
            } else if (type.equals(1)) {
                dto.setStringType("面试提醒");
            } else if (type.equals(2)) {
                dto.setStringType("报道提醒");
            } else if (type.equals(3)) {
                dto.setStringType("72小时内返佣未充值");
            } else {
                dto.setStringType("给付中断");
            }
            return dto;
        }).collect(Collectors.toList());


        List<Message> collect = messages1.stream().map(dto -> {

            Integer type = dto.getType();
            if (type.equals(0)) {
                dto.setStringType("平台处理结果通知");
            } else if (type.equals(1)) {
                dto.setStringType("面试提醒");
            } else if (type.equals(2)) {
                dto.setStringType("报道提醒");
            } else if (type.equals(3)) {
                dto.setStringType("72小时内返佣未充值");
            } else {
                dto.setStringType("给付中断");
            }
            return dto;
        }).collect(Collectors.toList());
        //代招单位
        map.put("noticeReferrerRepresentativeUnit", collect1);
        //招聘单位
        map.put("noticeReferrerRecruitmentUnit", collect);
        return map;
    }

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
            AssertUtil.isTrue(query.getType() != 0 || query.getType() != 1, RlzyConstant.OPS_FAILED_MSG);
        }
        attention.setCreatetime(LocalDateTime.now());
        return attentionMapper.insertSelective(attention);
    }
}