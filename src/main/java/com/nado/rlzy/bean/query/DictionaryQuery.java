package com.nado.rlzy.bean.query;

import lombok.Data;

/**
 * @ClassName 前端选项内容入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-08 17:34
 * @Version 1.0
 */
@Data
public class DictionaryQuery {
    /**
     * 岗位 1
     */
    private String post;

    /**
     * 用工形式(工作性质) 13
     */
    private String contractWayDetailId;

    /**
     * 专业 3
     */
    private String profession;

    /**
     * 学历 2
     */
    private String education;

    /**
     * 经验要求 12
     */
    private String experience;

    /**
     * 工作方式 16
     */
    private String workWay;

    /**
     * 工作时间 17
     */
    private String workTime;

    /**
     * 服装要求 14
     */
    private String clothingRequirement;

    /**
     * 兴趣爱好 15
     */
    private String hobby;

    /**
     * 加班时长 18
     */
    private String overtimeTimes;

    /**
     * 福利 10
     */
    private String welfare;

    /**
     * 5
     */
    private String manAge;

    /**
     * 9
     */
    private String femaleAge;

    private Integer userId;

}
