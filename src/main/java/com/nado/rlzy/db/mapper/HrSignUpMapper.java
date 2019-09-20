package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.dto.JobListDto;
import com.nado.rlzy.bean.dto.TaskHireWayDto;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.db.pojo.HrSignUp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HrSignUpMapper extends Mapper<HrSignUp> {


    /**
     * 报名表查询所有
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:04 2019/8/5
     * @Param [userId]
     **/
    List<HrSignUp> queryAll(@Param("userId") Integer userId);

    /**
     * 招聘端 查询求职列表概览
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:04 2019/7/2
     * @Param query 入参
     **/
    List<HrSignUp> selectJobListOverview(JobListQuery query);

    /**
     * 查看求职列表详情
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:18 2019/7/2
     * @Param [query]
     **/
    List<JobListDto> selectJobList(JobListQuery query);


    /**
     * 招聘端 个人中心 我的收藏 报名表信息 概览
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:21 2019/7/2
     * @Param []
     **/
    List<HrSignUp> selectCollectListOverview(@Param("userId") Integer userId);


    /**
     * 查询报名表详情
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:12 2019/7/8
     * @Param []
     **/
    HrSignUp selectSignUpTable(@Param("signId") Integer signId);

    /**
     * 查询报名表 概览
     *
     * @param userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:36 2019/7/8
     **/
    List<HrSignUp> selectSignUp(@Param("userId") Integer userId);


    /**
     * 查询投诉人 投诉人是报名该简章的求职者 用户身份是推荐人
     *
     * @param briefChapterId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:47 2019/7/10
     **/
    List<HrSignUp> searchSignUpUserName(@Param("briefChapterId") Integer briefChapterId, @Param("userId") Integer userId);


    /**
     * 查询报名者的名字 本人为报名者 和推荐者下的求职者
     *
     * @param type   求职端身份
     * @param userId 登录用户 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:44 2019/7/11
     **/
    List<HrSignUp> querySignUpUserName(@Param("type") Integer type, @Param("userId") Integer userId);

    /**
     * 查询投诉人 投诉人是推荐人下的投诉人
     *
     * @param typeId 身份类型id
     * @param userId 用户id
     * @param brieId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:01 2019/8/4
     **/
    List<ComplaintPage> queryComplaintPerson(@Param("typeId") Integer typeId, @Param("userId") Integer userId, @Param("brieId") Integer brieId);


    /**
     * 根据求职者的名字查询求职状态 废弃  求职状态 前台传过来
     *
     * @param id 求职者id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:54 2019/7/11
     **/
    List<HrSignUp> querySignUpStatus(@Param("id") Integer id);


    /**
     * 招聘端 查询报名了某个简章的人数
     *
     * @param briefchapter 简章id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:40 2019/7/17
     **/
    List<HrSignUp> countSignUpNum(@Param("briefchapter") Integer briefchapter);


    /**
     * 邀请报名
     *
     * @param signUpId 报名表id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:59 2019/7/17
     **/
    int invitationToRegister(@Param("signUpId") Integer signUpId);

    /**
     * 招聘端 不合适
     *
     * @param signUpId 报名 id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:19 2019/7/17
     **/
    int notSuitable(@Param("signUpId") Integer signUpId);

    /**
     * 邀请面试
     *
     * @param signUpId 报名id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:55 2019/7/17
     **/
    int invitationInterview(@Param("signUpId") Integer signUpId);

    /**
     * 招聘端 直接录取 查询面试的返费金额
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:08 2019/7/17
     * @Param [userId, signUpId]
     **/
    HrSignUp SearchdirectAdmission(@Param("signUpId") Integer signUpId, @Param("sex") Integer sex, @Param("briefchapter") Integer briefchapter);


    /**
     * 招聘详情概览
     *
     * @param list 招聘状态
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:05 2019/7/18
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
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:31 2019/7/18
     * @Param [signUpId]
     **/
    int updateJobStatus(@Param("signUpId") Integer signUpId);

    /**
     * 把待面试盖为未面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:13 2019/7/18
     * @Param [signUpId]
     **/
    int updateJobStatusInterviewed(@Param("signUpId") Integer signUpId);

    /**
     * 把待报道改为已报道
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:40 2019/7/18
     * @Param [report]
     **/
    int updateJobStatusConfirmationReport(@Param("report") Integer report);

    /**
     * 把待报道 改为 未报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:48 2019/7/18
     * @Param [report]
     **/
    int updateJobStatusReport(@Param("report") Integer report);

    /**
     * 把待报道改为已报道(待面试) 通过定时任务
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:47 2019/7/18
     * @Param [list]
     **/
    int updateJobStatusConfirmationReportByTask(@Param("list") List<HrSignUp> list);

    /**
     * 查询报明表id
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:33 2019/7/18
     * @Param []
     **/
    List<HrSignUp> signUpId();


    /**
     * 定时任务 返佣表待返佣 变成已返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:27 2019/7/18
     * @Param []
     **/
    int waitingForCommissionToBecomeARebate();

    /**
     * 定时任务 求职分发表 待返佣变成已返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:40 2019/8/26
     * @Param []
     **/
    int waitingForCommissionToBecomeRebateSIgnUp();

    /**
     * 招聘详情 已报名 不合适
     *
     * @param signUpId 报名 id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:57 2019/7/19
     **/
    int reportNotSuitable(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 已报名 邀请面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:13 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsInvitationInterview(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 已报名 直接录取
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:58 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsDirectAdmission(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:19 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewd(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 待面试 未面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:23 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentNoInterviewd(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试 面试未通过
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:45 2019/7/19
     * @Param [signUpId]
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si SET sd.no_pass_reason = 3, sd.job_status = 5, sd.status = 4" +
            " WHERE sd.delete_flag = 0 and si.delete_flag = 0 and si.id = sd.signup_id and si.id = #{signUpId} and sd.brief_chapter_id = #{briefChapterId}")
    int recruitmentInterviewFailed(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试 面试已通过
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:49 2019/7/19
     * @Param [signUpId]
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si SET sd.job_status = 5, sd.status = 2" +
            " WHERE sd.delete_flag = 0 and si.delete_flag = 0 and si.id = sd.signup_id and si.id = #{signUpId} and sd.brief_chapter_id = #{briefChapterId}")
    int recruitmentInterviewSuccess(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 查询 报到时间
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:00 2019/7/19
     * @Param []
     **/
    List<HrSignUp> recruitmentInterviewOver();


    /**
     * 定时任务: 把已面试改不合适和面试不通过
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:17 2019/7/19
     * @Param []
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si\n" +
            "set sd.no_pass_reason = 3,\n" +
            "    sd.status         = 4,\n" +
            "    sd.job_status     = 4\n" +
            "where sd.delete_flag = 0\n" +
            "  and si.delete_flag = 0\n" +
            "  and sd.status = 10")
    int updateInterviewStatus();

    /**
     * 招聘详情 待报道 未报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:31 2019/7/19
     * @Param [signUpId]
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si SET no_pass_reason = 2" +
            " WHERE sd.delete_flag = 0 and si.delete_flag = 0 and si.id = sd.signup_id and si.id = #{signUpId} and sd.brief_chapter_id = #{briefChapterId}")
    int notReported(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 招聘详情 待报道 确认报道 到待返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:37 2019/7/19
     * @Param [signUpId]
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si SET sd.job_status = 7, sd.status = 3" +
            " WHERE sd.delete_flag = 0 and si.delete_flag = 0 and si.id = sd.signup_id and si.id = #{signUpId} and sd.brief_chapter_id = #{briefChapterId}")
    int reported(@Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);


    /**
     * 未报到原因
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:59 2019/7/19
     * @Param [reason, signUpId]
     **/
    int noReportedReason(@Param("reason") Integer reason, @Param("signUpId") Integer signUpId, @Param("briefChapterId") Integer briefChapterId);


    /**
     * 招聘单位 扣的返佣的钱
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:00 2019/7/19
     * @Param [userId, money]
     **/
    int rebateAdd(RebateQuery query);

    /**
     * 求职者 和推荐者 返佣得到的钱
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:02 2019/7/19
     * @Param [userId, money]
     **/
    int rebateSubtraction(RebateQuery query);

    /**
     * 重置求职者的求职状态
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:55 2019/7/22
     * @Param [signUpId, status]
     **/
    @Update(value = "update hr_signup_deliveryrecord sd, hr_signup si\n" +
            "SET sd.job_status = #{status}\n" +
            "WHERE sd.delete_flag = 0\n" +
            "  and si.delete_flag = 0\n" +
            "  and si.id = sd.signup_id\n" +
            "  and si.id = #{signUpId}\n" +
            "  and sd.brief_chapter_id = #{briefChapterId}")
    int changeJobStatus(@Param("signUpId") Integer signUpId, @Param("status") Integer status, @Param("briefChapterId") Integer briefChapterId);

    /**
     * 返佣中断  未返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:38 2019/7/22
     * @Param [signUpId]
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
     *
     * @param groupName  自定义分组名字
     * @param signUpName 被推荐人姓名
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/7/22
     **/
    List<HrSignUp> grouper(@Param("groupName") String groupName, @Param("signUpName") String signUpName, @Param("userId") Integer userId);

    /**
     * 查询求职端身份是推荐人 首页 我的求职表下被推荐人的求职表
     *
     * @param signUpName 被推荐人的姓名
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:33 2019/7/22
     **/
    List<HrSignUp> selectSignUpTableBySignUpName(@Param("signUpName") String signUpName, @Param("userId") Integer userId);

    /**
     * 求职端 个人中心 我的报名表 查询推荐人名下的报名表
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:15 2019-09-15
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     */
    List<HrSignUp> selectAllSignUpByRecommend(@Param("userId") Integer userId);


    /**
     * 求职端 求职表 我的求职表 确认报名 废弃...
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/7/22
     * @Param [briefChapterId, id]
     **/
    int confirmRegistration(@Param("briefChapterId") Integer briefChapterId, @Param("id") List<Integer> id);

    /**
     * 查询最近连续三次的求职状态
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:35 2019/8/16
     * @Param [signId]
     **/
    List<HrSignUp> threeNoInterview(@Param("signId") Integer signId);

    /**
     * 公司主页 历史记录 简章的报名人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:00 2019/8/26
     * @Param [briefchapterId]
     **/
    int briefchaterSignUpNo(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 公司主页 历史记录 简章的面试人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:04 2019/8/26
     * @Param [briefchapterId]
     **/
    int briefchapterInterviewNo(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 公司主页 历史记录 简章的报道人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:08 2019/8/26
     * @Param [briefchapterId]
     **/
    int briefchapterReportNo(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 公司主页 历史记录 简章的面试返费人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:16 2019/8/26
     * @Param [briefchapterId]
     **/
    int rebateInterview(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 公司主页 历史记录 简章的报道返费人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:18 2019/8/26
     * @Param [briefchapterId]
     **/
    int rebateReport(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 公司主页 历史记录 简章的入职返费人数 (代招 和招聘单位都是)
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:23 2019/8/26
     * @Param [briefchapterId]
     **/
    int rebateEntry(@Param("briefchapterId") Integer briefchapterId);


    List<HrSignUp> aaa(@Param("id") String id);

    /**
     * 完全直录
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:51 2019/9/20
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<TaskHireWayDto> fullyDirectRecording();

    /**
     * 不可直录
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:52 2019/9/20
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<TaskHireWayDto> notDirectlyRecorded();

    /**
     * 可以直录
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:53 2019/9/20
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<TaskHireWayDto> canRecordDirectly();


}