package com.nado.rlzy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName 云通讯配置类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/16 11:41
 * @Version 1.0
 */
@Configuration
public class YunTongXunConfig {
    @Value("${yuntongxun.accountSid}")
    private String accountSid;

    @Value("${yuntongxun.accountToken}")
    private String accountToken;

    @Value("${yuntongxun.appId}")
    private String appId;

    @Value("${yuntongxun.templateId}")
    private String templateId;
}