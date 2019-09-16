package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.math.BigDecimal;
import java.util.List;

public interface HrSignupDeliveryrecordMapper extends Mapper<HrSignupDeliveryrecord>, MySqlMapper<HrSignupDeliveryrecord> {

    /**
     * 推荐人给被推荐人报名
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:28 2019/8/7
     * @Param [entites]
     * @return int
     **/
    int referrerToSIgnUp(@Param("entites") List<HrSignupDeliveryrecord> entites);


    /**
     * 待返佣金额减少 面试返佣状态变成已返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:58 2019/9/2
     * @Param [deliveryrecord]
     * @return int
     **/
    int reducedRebateAmount(HrSignupDeliveryrecord deliveryrecord);

    /**
     * 查询推荐人下的报名者的违规人数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:43 2019/9/4
     * @Param [userId]
     * @return int
     **/
    int selectNumberViolations(@Param("userId") Integer userId);

    /**
     * 修改返佣状态为返佣已完成
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:46 2019/9/5
     * @Param
     * @return
     **/
    int updateReba(@Param("signupDeliveryrecordId") Integer signupDeliveryrecordId);

    /**
     * 入职返佣完成一笔 待返佣金额就减去一笔
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:04 2019/9/5
     * @Param
     * @return int
     **/
    int updateWaitingForCommission(@Param("acceptRebateAmount") BigDecimal acceptRebateAmount, @Param("briefchpterId") Integer briefchpterId, @Param("signUpId") Integer signUpId);


    /**
     * 求职端我的工作 取消报名 | 取消报道 | 取消面试 | 放弃
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 21:30 2019-09-10
     * @Param [hsdId]
     * @return int
     */
    int cancelRegistration(@Param("id") Integer id);

    /**
     * 推荐人给被推荐人报名
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 10:54 2019-09-16
     * @Param [list]
     * @return int
     */
    int inserttListt(@Param("list") List<HrSignupDeliveryrecord> list);




}