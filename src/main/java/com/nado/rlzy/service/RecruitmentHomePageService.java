package com.nado.rlzy.service;

import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.pojo.Collect;

import java.util.List;

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
    List<JobListtFront> selectJobListOverview(JobListQuery query);


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
     * 添加收藏 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:22 2019/7/2
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.query.JobListQuery>
     **/
    List<JobListtFront> selectCollectListOverview(Integer userId );

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
    void save(Collect collect);

    /**
     * 招聘端 取消收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:41 2019/7/8
     * @Param [collect]
     * @return void
     **/
    void updateSignUpCollectStatus(Collect collect);

}