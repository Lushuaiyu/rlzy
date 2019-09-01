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
     * 编辑简章时, 招聘人数减少, 钱退到账户 废弃
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:26 2019/7/24
     * @Param [userId]
     * @return int
     **/
    int returnMoney(@Param("userId") Integer userId, @Param("money") BigDecimal money);

    /**
     * 编辑简章时, 招聘人数减少 | 有返佣 到 没返佣 , 钱退到账户余额 账户余额增加钱 冻结账户减少钱
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:28 2019/8/28
     * @param userId 用户id
     * @param rebateMoney 返佣退到账户的钱
     * @return int
     **/
    int reimbursement(@Param("userId") Integer userId, @Param("rebateMoney") BigDecimal rebateMoney);

    /**
     * 编辑简章时, 招聘人数增加, 钱到冻结账户 账户余额减少钱 冻结账户增加钱
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:33 2019/8/28
     * @Param [userId, rebateMoney]
     * @return int
     **/
    int reimbursementAddNum(@Param("userId") Integer userId, @Param("rebateMoney") BigDecimal rebateMoney);

    /**
     * 查询登录用户的资金情况 如果账户余额钱不够 那就要去充钱
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:29 2019/8/28
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrAcct>
     **/
    int selectAcct(@Param("userId") Integer userId);

    /**
     * 面试 报道 入职返佣 钱从公司账户到用户账户  这里是用户账户增加钱
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 14:43 2019-09-01
     * @Param [userId, money]
     * @return int
     */
    int rebateUser(@Param("userMoney") BigDecimal userMoney, @Param("userId") Integer userId );

    /**
     * 面试 报道 入职返佣 钱从商家账户到用户账户  这里是公司账户减少的钱 钱到消费金额
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:21 2019/9/1
     * @Param [userId, businessMoney]
     * @return int
     **/
    int rebateBusiness(@Param("businessMoney") BigDecimal businessMoney, @Param("userId") Integer userId );




}