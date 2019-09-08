package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.utils.telMessage.VaildateType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

public interface HrRebaterecordMapper {


    /**
     * 发布简章 返佣金额 添加
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:45 2019/7/23
     * @Param [query]
     **/
    int insertSelective(@Param("list") List<ReleaseBriefcharpterQuery> list);


    HrRebaterecord selectByPrimaryK(Integer id);

    int updateByPrimaryKeySelecti(HrRebaterecord record);


    /**
     * 定时任务 返佣 查询入职返佣时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:29 2019/7/18
     * @Param []
     **/
    List<HrRebaterecord> selectRebateTime();

    /**
     * 查询 男 和 女 单人面试 报道 入职的总共返佣金额
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:46 2019/7/23
     * @Param [briefchapter]
     **/
    List<HrRebaterecord> manOrWomenRebate(@Param("briefchapter") Integer briefchapter);

    /**
     * 增加招聘人数时 修改返佣价格 废弃 应该修改简章表的价格
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:05 2019/7/23
     * @Param [rebateFemaleVariable, rebateManVariable, briefchapter]
     **/
    int updateRebateMoney(@Param("rebateVariable") BigDecimal rebateVariable,
                          @Param("briefchapter") Integer briefchapter,
                          @Param("rebateType") Integer rebateType);


    /**
     * 招聘端 我的发布 修改返佣价格
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:57 2019/7/24
     * @Param [manMoney, womenMoney, type, briefchapter]
     **/
    int updateRebateManAndWomen(@Param("manMoney") BigDecimal manMoney,
                                @Param("womenMoney") BigDecimal womenMoney,
                                @Param("type") Integer type,
                                @Param("briefchapter") Integer briefchapter);

    /**
     * 批量更新
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:38 2019/8/21
     * @Param [lists]
     **/
    int updateBatch(List<HrRebaterecord> lists);

    /**
     * 招聘端 我的发布 招聘详情 待面试 已面试 | 待报到 已报到 改变返佣状态 and 增加返佣时间
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:47 2019/8/21
     * @Param [reId]
     **/
    int updateRebateStatus(HrRebaterecord rebaterecord);

    /**
     * 招聘端 我的发布 招聘详情 待面试 已面试 | 待报到 已报到 查询返佣表id
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:48 2019/8/21
     * @Param [briefchapterId, rebateType]
     **/
    int selectReId(HrRebaterecord rebaterecord);

    /**
     * 发布简章 返佣金额 添加 添加入职返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:45 2019/7/23
     * @Param [query]
     **/
    int insertListt(@Param("list") List<HrRebaterecord> list);

    /**
     * 招聘详情 待返佣 查询返佣
     *
     * @param signUpId 报名投递表id
     * @param briefchapterId 简章id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:56 2019/7/19
     **/
    List<HrRebaterecord> rebate(@Param("signUpId") Integer signUpId, @Param("briefchapterId") Integer briefchapterId);


    /**
     * 招聘端 招聘详情 待返佣 返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:04 2019/7/30
     * @Param []
     **/
    int rebateOne(@Param("rebateId") Integer rebateId, @Param("signupDeliveryrecordId") Integer signupDeliveryrecordId);

    /**
     * 招聘详情 待返佣 不返佣
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:29 2019/7/19
     * @Param [rebateId]
     **/
    @Update(value = "update hr_rebaterecord set status = 13 where DeleteFlag = 0 and id = #{rebateId}")
    int noRebate(@Param("rebateId") Integer rebateId);


    /**
     * 根据status 为待报到 查询 和报道返佣相关的信息
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:09 2019/7/18
     * @Param []
     **/
    List<HrRebaterecord> signUpIdByReport();


    /**
     * 查询入职返佣有没有全部返费完成
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:19 2019/9/5
     * @Param [signUpId]
     * @return int
     **/
    List<Integer> selectNotStatusRebate(@Param("userId") Integer userId);

    /**
     * 个人 代招单位 返佣中断
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 10:23 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     */
    List<HrRebaterecord> selectRebateInterruptMyself();

    /**
     * 个人 招聘单位返佣中断
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 12:33 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     */
    List<HrRebaterecord> selectRebateInterruptMyselfRepresentative();

    /**
     * 推荐人 代招单位 返佣中断
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 12:56 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     */
    List<HrRebaterecord> selectRebateReferrer();

    /**
     * 推荐人 招聘单位 返佣中断
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 12:58 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     */
    List<HrRebaterecord> selectRebateReferrerRepresentative();


    /**
     * 查询所有的报道返佣
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 11:27 2019-09-08
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     */
    List<HrRebaterecord> selectAllReportInformation();

    /**
     * 查询所有的入职返佣
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 11:27 2019-09-08
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     */
    List<HrRebaterecord> selectAllEntryInformation();


}