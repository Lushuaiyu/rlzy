package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrSignUp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.math.BigDecimal;
import java.util.List;

public interface HrRebaterecordMapper  {


    /**
     * 发布简章 返佣金额 添加
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:45 2019/7/23
     * @Param [query]
     * @return int
     **/
    int insertSelective(@Param("list") List<ReleaseBriefcharpterQuery> list);


    HrRebaterecord selectByPrimaryK(Integer id);

    int updateByPrimaryKeySelecti(HrRebaterecord record);


    /**
     * 定时任务 返佣 查询返佣时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:29 2019/7/18
     * @Param []
     **/
    List<HrSignUp> selectRebateTime();

    /**
     * 查询 男 和 女 单人面试 报道 入职的总共返佣金额
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:46 2019/7/23
     * @Param [briefchapter]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     **/
    List<HrRebaterecord> manOrWomenRebate(@Param("briefchapter") Integer briefchapter);

    /**
     * 增加招聘人数时 修改返佣价格 废弃 应该修改简章表的价格
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:05 2019/7/23
     * @Param [rebateFemaleVariable, rebateManVariable, briefchapter]
     * @return int
     **/
    int updateRebateMoney(@Param("rebateVariable") BigDecimal rebateVariable,
                          @Param("briefchapter") Integer briefchapter,
                          @Param("rebateType") Integer rebateType);

    @Update(value = "update hr_rebaterecord set RebateMale = #{rebateMale}, RebateFemale = #{rebateFemale} where BriefChapterId = #{brId} and DeleteFlag = 0")
    int updateReba(@Param("rebateMale") BigDecimal rebateMale, @Param("rebateFemale") BigDecimal rebateFemale , @Param("brId") Integer brId);

    /**
     * 招聘端 我的发布 修改返佣价格
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:57 2019/7/24
     * @Param [manMoney, womenMoney, type, briefchapter]
     * @return int
     **/
    int updateRebateManAndWomen(@Param("manMoney") BigDecimal manMoney,
                                @Param("womenMoney") BigDecimal womenMoney,
                                @Param("type") Integer type,
                                @Param("briefchapter") Integer briefchapter);

    /**
     * 批量更新
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:38 2019/8/21
     * @Param [lists]
     * @return int
     **/
    int updateBatch( List<HrRebaterecord> lists);

    /**
     * 招聘端 我的发布 招聘详情 待面试 已面试 | 待报到 已报到 改变返佣状态 and 增加返佣时间
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:47 2019/8/21
     * @Param [reId]
     * @return int
     **/
    int updateRebateStatus(HrRebaterecord rebaterecord);

    /**
     *  招聘端 我的发布 招聘详情 待面试 已面试 | 待报到 已报到 查询返佣表id
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:48 2019/8/21
     * @Param [briefchapterId, rebateType]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrRebaterecord>
     **/
    int selectReId(HrRebaterecord rebaterecord);

    /**
     * 发布简章 返佣金额 添加 添加入职返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:45 2019/7/23
     * @Param [query]
     * @return int
     **/
    int insertListt(@Param("list") List<HrRebaterecord> list);







}