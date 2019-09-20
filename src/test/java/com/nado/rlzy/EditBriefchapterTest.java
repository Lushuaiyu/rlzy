package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.HrAcctMapper;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName 编辑简章 测试类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/23 14:40
 * @Version 1.0
 */
public class EditBriefchapterTest extends BaseTest {

    @Resource
    private HrBriefchapterMapper briefchapterMapper;

    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrAcctMapper acctMapper;
    BriefcharpterQuery query = new BriefcharpterQuery();
    Integer i = 4;





    @Test
    public void test3() {
        BigDecimal a = BigDecimal.valueOf(900);
        BigDecimal b = BigDecimal.valueOf(4005);


    }
}