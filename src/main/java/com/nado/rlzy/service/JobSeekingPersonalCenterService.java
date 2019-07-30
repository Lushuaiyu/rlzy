package com.nado.rlzy.service;

import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;

import java.util.List;

/**
 * @ClassName 求职端个人中心
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 14:58
 * @Version 1.0
 */

public interface JobSeekingPersonalCenterService {
    /**
     * 简章收藏概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:32 2019/7/5
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentBrochureCollection(Integer userId);

    /**
     * 查询我的报名表详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:19 2019/7/8
     * @Param [signId, userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUpTable(Integer signId, Integer userId);


    /**
     * 查询我的报名表概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/7/8
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUp( Integer userId);

    /** 查询投诉人 投诉人是报名该简章的求职者
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:56 2019/7/10
     * @param briefChapterId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> searchSignUpUserName(Integer briefChapterId);


}