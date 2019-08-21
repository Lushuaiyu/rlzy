package com.nado.rlzy.bean.query;

import lombok.Data;

/**
 * @ClassName 招聘端 推荐人query
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/15 10:28
 * @Version 1.0
 */
@Data
public class ReferrerQuery {

    /**
     * 推荐人id
     */
    private String id;

    /**
     * 推荐人数下限
     */
    private String recommendNoLower;

    /**
     * 推荐人数上限
     */
    private String recommendNoUpper;

    /**
     * 意向岗位 简章表的postId
     */
    private String postId;

}