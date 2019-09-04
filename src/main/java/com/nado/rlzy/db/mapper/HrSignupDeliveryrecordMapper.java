package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

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



}