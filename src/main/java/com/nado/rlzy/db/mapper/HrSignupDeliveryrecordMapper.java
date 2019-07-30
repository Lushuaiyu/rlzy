package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface HrSignupDeliveryrecordMapper extends Mapper<HrSignupDeliveryrecord> {
    /**
     * 我要报名 身份是本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:03 2019/7/14
     * @Param [userId, typeId, signUpStatus]
     * @return int
     **/
    int IWantToSignUp (@Param("userId") Integer userId, @Param("briefChapterId") Integer briefChapterId);
}