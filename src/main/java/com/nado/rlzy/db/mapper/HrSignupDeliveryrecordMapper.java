package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
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
    int referrerToSIgnUp(List<HrSignupDeliveryrecord> entites);
}