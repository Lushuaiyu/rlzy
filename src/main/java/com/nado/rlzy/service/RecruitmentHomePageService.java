package com.nado.rlzy.service;

import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.pojo.Collect;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘版首页
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/2 14:03
 * @Version 1.0
 */

public interface RecruitmentHomePageService {

    /**
     * 查询求职列表概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:05 2019/7/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<HrSignUp> selectJobListOverview(JobListQuery query);


    /**
     * 查询求职列表详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:28 2019/7/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<JobListtFront> selectJobList(JobListQuery query);

    /**
     * 招聘端 个人中心 我的收藏 报名表信息 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:22 2019/7/2
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.query.JobListQuery>
     **/
    List<HrSignUp> selectCollectListOverview(Integer userId );

    /**
     * 添加收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:27 2019/7/2
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.frontEnd.JobListtFront>
     **/
    /*List<JobListtFront> selectCollectList();*/


    /**
     * 招聘端 添加搜藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:20 2019/7/2
     * @Param briefcharpter 简章id
     * @param  collect 收藏 对象 入参
     * @return void
     **/
    int save(Collect collect);

    /**
     * 招聘端 取消收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:41 2019/7/8
     * @Param [collect]
     * @return void
     **/
    int updateSignUpCollectStatus(Collect collect);

    /**
     * 招聘端 首页简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:16 2019/8/14
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    Map<String, Object> recruitmentBriefchapter(Integer userId);

    /**
     * 招聘端首页简章 子账号
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:42 2019/9/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    HashMap<String, Object> representativeUnitSubAccount(Integer userId);

    /**
     * 招聘端首页 推荐人列表概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:40 2019/8/15
     * @Param [query, referrerQuery]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<HrUser> referrer(JobListQuery query);

    /**
     * 招聘端首页 推荐人列表详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:59 2019/8/15
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> referrerDetails(Integer userId);

    /**
     * 招聘端求职者列表 添加搜藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:15 2019/8/15
     * @Param [userId, signUpId]
     * @return int
     **/
    int collectSignUPTable(Integer userId, Integer signUpId);

    /**
     * 招聘端求职者列表 取消搜藏 | 招聘端推荐人列表 取消搜藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:16 2019/8/15
     * @Param [id]
     * @return int
     **/
    int collectCancel(Integer id);

    /**
     * 招聘端推荐人列表 添加搜藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:48 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int collectReferrer(Integer userId);

    /**
     * 查询企业认证状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:00 2019/9/19
     * @Param [userId]
     * @return int
     **/
    Integer selectCoCertificationStatus(Integer userId);

    /**
     * 查询身份
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:39 2019/9/19
     * @Param [userId]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser checkUserIdentity(Integer userId);

    /**
     * 子账号权限查询
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:49 2019/9/19
     * @Param [userId]
     * @return java.lang.String
     **/
    String subAccountPermission(Integer userId);


}