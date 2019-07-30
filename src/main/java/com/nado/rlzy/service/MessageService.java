package com.nado.rlzy.service;

/**
 * @ClassName 发送短信 service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/16 10:31
 * @Version 1.0
 */
public interface MessageService {
     void sendMessage(String phone, Integer type );
}