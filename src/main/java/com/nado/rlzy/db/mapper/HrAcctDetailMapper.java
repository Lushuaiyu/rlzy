package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrAcctDetail;

public interface HrAcctDetailMapper {
    int insertSelective(HrAcctDetail record);

    HrAcctDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrAcctDetail record);


    /**
     * 账户明细表添加推荐人面试返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:52 2019/7/17
     * @param detail 入参
     * @return
     **/
    int accountDetailTableAddsReferrerRebate (HrAcctDetail detail);
}