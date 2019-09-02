package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrAcctDetail;
import tk.mybatis.mapper.common.Mapper;

public interface HrAcctDetailMapper extends Mapper<HrAcctDetail> {


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