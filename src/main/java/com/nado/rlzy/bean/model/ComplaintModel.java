package com.nado.rlzy.bean.model;

import lombok.Data;

/**
 * @ClassName 投诉model
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/10 16:01
 * @Version 1.0
 */
@Data
public class ComplaintModel {
    /**
     * 登录用户
     */
    private String userName;

    /**
     * 被推荐者(求职者)
     */
    private String signUserName;
}