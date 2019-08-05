package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrAcct;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

/**
 * @author lushuaiyu
 */
public interface HrAcctMapper extends Mapper<HrAcct> {




    /**
     * 把面试的钱返给推荐者
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:22 2019/7/17
     * @Param [userId]
     * @return int
     **/
    int directAdmission(@Param("userId") Integer userId, @Param("money") BigDecimal money);

    /**
     * 编辑简章时, 招聘人数减少, 钱退到账户
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:26 2019/7/24
     * @Param [userId]
     * @return int
     **/
    int returnMoney(@Param("userId") Integer userId, @Param("money") BigDecimal money);
}