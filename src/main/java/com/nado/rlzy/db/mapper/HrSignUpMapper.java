package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.dto.JobListDto;
import com.nado.rlzy.bean.dto.SignUpNumberDto;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.db.pojo.HrSignUp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HrSignUpMapper extends Mapper<HrSignUp> {
    int insertSe(HrSignUp record);
    int  updateBy(HrSignUp signUp);

    /**
     * 报名表查询所有
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:04 2019/8/5
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> queryAll(@Param("userId") Integer userId, @Param("type") Integer type);



    /**
     * 编辑个人资料 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:49 2019/7/9
     * @Param [userId, typeId]
     * @return int
     **/
    int updatePesronInformation( HrSignUp signUp);


    /**
     * 查询求职列表概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:04 2019/7/2
     * @Param query 入参
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<JobListDto> selectJobListOverview(JobListQuery query);




    /**
     * 查看求职列表详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:18 2019/7/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<JobListDto> selectJobList(JobListQuery query);


    /**
     * 添加收藏 概览 可能用不到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:21 2019/7/2
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<JobListDto> selectCollectListOverview(@Param("userId") Integer userId);



    /**
     * 添加收藏 废弃
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:26 2019/7/2
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<JobListDto> selectCollectList();


    /**
     * 查询报名表详情 可能废弃
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:12 2019/7/8
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUpTable(@Param("signId") Integer signId, @Param("userId") Integer userId);

    /**
     * 查询报名表 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:36 2019/7/8
     * @param  userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUp(@Param("userId") Integer userId);







    /**
     * 查询投诉人 投诉人是报名该简章的求职者 用户身份是推荐人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:47 2019/7/10
     * @param briefChapterId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> searchSignUpUserName(@Param("briefChapterId") Integer briefChapterId, @Param("userId") Integer userId);



    /**
     *  查询报名者的名字 本人为报名者 和推荐者下的求职者
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:44 2019/7/11
     * @param type 求职端身份
     * @param userId 登录用户 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> querySignUpUserName(@Param("type") Integer type, @Param("userId") Integer userId);

    /** 查询投诉人 投诉人是推荐人下的投诉人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:01 2019/8/4
     * @param typeId 身份类型id
     * @param userId 用户id
     * @param brieId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<ComplaintPage> queryComplaintPerson(@Param("typeId") Integer typeId, @Param("userId") Integer userId, @Param("brieId") Integer brieId);




    /**
     * 根据求职者的名字查询求职状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:54 2019/7/11
     * @param jobUserName 求职者姓名
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> querySignUpStatus(@Param("jobUserName") String jobUserName);




    /**
     * 招聘端 查询报名人数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:40 2019/7/17
     * @param briefchapter 简章id
     * @return int
     **/
    List<SignUpNumberDto> countSignUpNum(@Param("briefchapter") Integer briefchapter);

    /**
     *  邀请报名
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:59 2019/7/17
     * @param signUpId 报名表id
     * @return int
     **/
    int invitationToRegister (@Param("signUpId") Integer signUpId);

    /**
     * 招聘端 不合适
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:19 2019/7/17
     * @param signUpId 报名 id
     * @return int
     **/
    int notSuitable (@Param("signUpId") Integer signUpId);

    /**
     * 邀请面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:55 2019/7/17
     * @param signUpId 报名id
     * @return int
     **/
    int invitationInterview (@Param("signUpId") Integer signUpId);

    /**
     *  招聘端 直接录取 查询面试的返费金额
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:08 2019/7/17
     * @Param [userId, signUpId]
     * @return int
     **/
    HrSignUp SearchdirectAdmission(@Param("signUpId") Integer signUpId, @Param("sex") Integer sex);


    /**
     * 招聘详情概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:05 2019/7/18
     * @param list 招聘状态
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> recruitmentDetailsOverview(@Param("list") List<Integer> list);

   /* *//**
     * 把待面试改为已面试 定时任务
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:08 2019/7/18
     * @Param [signUpId]
     * @return int
     **//*
    int updateJobStatusByTask(@Param("id") List<Integer> id);*/

    /**
     * 把待面试改为已面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:31 2019/7/18
     * @Param [signUpId]
     * @return int
     **/
    int updateJobStatus(@Param("signUpId") Integer signUpId);

    /**
     * 把待面试盖为未面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:13 2019/7/18
     * @Param [signUpId]
     * @return int
     **/
    int updateJobStatusInterviewed(@Param("signUpId") Integer signUpId);

    /**
     * 把待报道改为已报道
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:40 2019/7/18
     * @Param [report]
     * @return int
     **/
    int updateJobStatusConfirmationReport(@Param("report") Integer report);

    /**
     * 把待报道 改为 未报到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:48 2019/7/18
     * @Param [report]
     * @return int
     **/
    int updateJobStatusReport(@Param("report") Integer report);

    /**
     * 把待报道改为已报道 通过定时任务
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:47 2019/7/18
     * @Param [list]
     * @return int
     **/
    int updateJobStatusConfirmationReportByTask(@Param("list") List<HrSignUp> list);

    /**
     * 查询报明表id
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:33 2019/7/18
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> signUpId();

    /**
     * 根据jobStatus 为待报道的查id
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:09 2019/7/18
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> signUpIdByReport();


    /**
     * 定时任务 返佣 查询返佣时间
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:29 2019/7/18
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectRebateTime();

    /**
     * 定时任务 待返佣 变成已返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:27 2019/7/18
     * @Param []
     * @return int
     **/
    int waitingForCommissionToBecomeARebate();

    /**
     * 招聘详情 已报名 不合适
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:57 2019/7/19
     * @param signUpId 报名 id
     * @return int
     **/
    int reportNotSuitable(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 已报名 邀请面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:13 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int recruitmentDetailsInvitationInterview(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 已报名 直接录取
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:58 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int recruitmentDetailsDirectAdmission(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待面试 已面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:19 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int recruitmentInterviewd(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待面试 未面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:23 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int recruitmentNoInterviewd(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待面试 已面试 面试未通过
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:45 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    @Update(value = "update hr_signup set no_pass_reason = 3, job_status = 4 where delete_flag = 0 and id = #{signUpId}")
    int recruitmentInterviewFailed(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待面试 已面试 面试已通过
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:49 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    @Update(value = "update hr_signup set job_status = 2 where  delete_flag = 0 and id = #{signUpId}")
    int recruitmentInterviewSuccess(@Param("signUpId") Integer signUpId);

    /**
     * 查询 报到时间
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:00 2019/7/19
     * @Param []
     * @return int
     **/
    List<HrSignUp> recruitmentInterviewOver();


    /**
     * 定时任务: 把已完成面试改为不合适 和面试不通过
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:17 2019/7/19
     * @Param []
     * @return int
     **/
    @Update(value = "update hr_signup set job_status = 4, no_pass_reason = 3 where  delete_flag = 0 and job_status = 10")
    int updateInterviewStatus();

    /**
     * 招聘详情 待报道 未报到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:31 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    @Update(value = "update hr_signup set job_status = 9 where delete_flag = 0 and id = #{signUpId}")
    int notReported(@Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待报道 已报道
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:37 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    @Update(value = "update hr_signup set job_status = 3 where delete_flag = 0 and id = #{signUpId}")
    int reported(@Param("signUpId") Integer signUpId);


    /**
     * 未报到原因
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:59 2019/7/19
     * @Param [reason, signUpId]
     * @return int
     **/
    int noReportedReason(@Param("reason") Integer reason, @Param("signUpId") Integer signUpId);

    /**
     * 招聘详情 待返佣 返佣计划
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:56 2019/7/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> rebate(@Param("userId") Integer userId);


    /**
     * 招聘详情 待返佣 没有返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:29 2019/7/19
     * @Param [rebateId]
     * @return int
     **/
    @Update(value = "update hr_rebaterecord set status = 2 where DeleteFlag = 0 and id = #{rebateId}")
    int noRebate(@Param("rebateId") Integer rebateId);


/**
 * 招聘端 招聘详情 待返佣 返佣
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 18:04 2019/7/30
 * @Param []
 * @return int
 **/
    int rebateOne(@Param("rebateId") Integer rebateId);

    /**
     * 招聘单位 扣的返佣的钱
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:00 2019/7/19
     * @Param [userId, money]
     * @return int
     **/
    //@Update(value = "update hr_acct set AcctBalance = (AcctBalance + #{addMoney} ) where DeleteFlag = 0 and UserId = #{userId}")
    int rebateAdd(RebateQuery query);

    /**
     * 求职者 和推荐者 返佣得到的钱
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:02 2019/7/19
     * @Param [userId, money]
     * @return int
     **/
    /*@Update(value = "update hr_acct set IceBalance = (IceBalance - #{subtraction}) where DeleteFlag = 0 and UserId = #{userId}")*/
    int rebateSubtraction(RebateQuery query);

    /**
     * 重置求职者的求职状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:55 2019/7/22
     * @Param [signUpId, status]
     * @return int
     **/
    @Update(value = "update hr_signup set job_status = #{status} where delete_flag = 0 and id = #{signUpId}")
    int changeJobStatus(@Param("signUpId") Integer signUpId, @Param("status") Integer status);

    /**
     * 返佣中断  未返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:38 2019/7/22
     * @Param [signUpId]
     * @return int
     **/
    @Update(value = "update hr_rebaterecord re inner join (select id, brief_chapter_id\n" +
            "                                      from hr_signup\n" +
            "                                      where delete_flag = 0\n" +
            "                                        and no_pass_reason = 4\n" +
            "                                        and id = #{signUpId}) si on re.BriefChapterId = si.brief_chapter_id\n" +
            "set re.status = 4\n" +
            "where re.DeleteFlag = 0\n" +
            "  and re.status = 2")
    int commissionRebate(@Param("signUpId") Integer signUpId);

    /**
     * 查询 求职端 身份是推荐人 首页 我的求职表 分组下的 被推荐人的报名表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/7/22
     * @param groupName 自定义分组名字
     * @param signUpName 被推荐人姓名
     * @return
     **/
    List<HrSignUp> grouper(@Param("groupName") String groupName, @Param("signUpName") String signUpName);

    /**
     *  查询求职端身份是推荐人 首页 我的求职表下被推荐人的求职表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:33 2019/7/22
     * @param signUpName 被推荐人的姓名
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> selectSignUpTableBySignUpName(@Param("signUpName") String signUpName);


    /**
     * 求职端 求职表 我的求职表 确认报名
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/7/22
     * @Param [briefChapterId, id]
     * @return int
     **/
    int confirmRegistration(@Param("briefChapterId") Integer briefChapterId, @Param("id") List<Integer> id);





}