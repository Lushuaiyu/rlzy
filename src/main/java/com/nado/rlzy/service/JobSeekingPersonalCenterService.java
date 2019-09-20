package com.nado.rlzy.service;

import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;

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
     * 简章收藏概览 代招企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:32 2019/7/5
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentBrochureCollection(Integer userId, Integer type);

    /**
     * 简章收藏概览 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:09 2019/8/2
     * @Param [userId, type]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentBrochureCollectionRecruitment(Integer userId,  Integer type);


    /**
     * 求职端个人中心 查询我的报名表详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:19 2019/7/8
     * @Param [signId, userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    HrSignUp selectSignUpTable(Integer signId);


    /**
     * 求职端个人中心 查询我的报名表概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/7/8
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUp( Integer userId);


    /**
     * 查询本人的违规
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:33 2019/8/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> queryMyselfVillation(Integer userId);

    /**
     * 求职端 个人中心  推荐人推荐的求职者违规
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:28 2019/8/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> queryReferrerVillation(Integer userId);








}