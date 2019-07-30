package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.HrAcctMapper;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.utils.CollectorsUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName 编辑简章 测试类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/23 14:40
 * @Version 1.0
 */
public class EditBriefchapterTest extends BaseTest {

    @Autowired
    private HrBriefchapterMapper briefchapterMapper;

    @Autowired
    private HrRebaterecordMapper rebaterecordMapper;

    @Autowired
    private HrAcctMapper acctMapper;
    BriefcharpterQuery query = new BriefcharpterQuery();
    Integer i = 4;

    @Test
    public void test1() {
        query.setBriefcharpterId(i);
        List<HrBriefchapter> list = briefchapterMapper.queryBriefcharpterDetileByParams(query);

        list.stream()
                .map(m -> {
                    Map<Integer, BigDecimal> map = m.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    //对返佣金额进行 foreach 操作, set到返回的结果集里
                    map.forEach((k, v) -> {
                        m.setRebateRecord(v);
                    });
                    System.out.println(m.getRebateRecord());
                    return m;
                })
                .collect(Collectors.toList());
    }

    /**
     * 招聘端 编辑简章
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:59 2019/7/23
     * @Param []
     **/
    @Test
    public void test2() {
        //现在返佣的钱
        BigDecimal rebateNowMan = BigDecimal.valueOf(5000);
        BigDecimal rebateNowWomen = BigDecimal.valueOf(600);
        //编辑简章时 招聘男生 多少
        Integer manNumNow = 28;
        // 编辑简章时 招聘女生多少 人
        Integer womenNumNow = 10;

        List<HrBriefchapter> list = briefchapterMapper.queryBriefcharpterDetileByParams(query);
        list.stream()
                .map(m -> {
                    //发布简章时 招聘的男生数量
                    Integer ManNum = m.getManNum();
                    // 发布简章时 招聘的女生数量
                    Integer womenNum = m.getWomenNum();
                    //编辑简章后 少招多少人 男
                    Integer editManNum = ManNum - manNumNow;
                    editManNum = Math.abs(editManNum);

                    // 编辑简章后 多招多少人 男
                    Integer editManNum1 = manNumNow - ManNum;
                    editManNum1 = Math.abs(editManNum1);

                    // 编辑简章后  少招多少人 女
                    Integer editWomenNum = womenNum - womenNumNow;
                    editWomenNum = Math.abs(editWomenNum);
                    //编辑简章时 多招多少人 女
                    Integer editWomenNum2 = womenNumNow - womenNum;
                    editWomenNum2 = Math.abs(editWomenNum2);
                    Integer finalEditManNum = editManNum1;
                    Integer finalEditWomenNum = editWomenNum2;
                    Integer finalEditManNum1 = editManNum;
                    Integer finalEditWomenNum1 = editWomenNum;
                    m.getRebat()
                            .stream()
                            .map(u -> {
                                //如果用户输入的返佣金额 < 1000 那就增加 用户输入的钱 > 1000 就  / 10
                                BigDecimal man = rebateNowMan.compareTo(BigDecimal.valueOf(1000)) < 0 ? rebateNowMan : rebateNowMan.divide(BigDecimal.valueOf(10));
                                BigDecimal women = rebateNowWomen.compareTo(BigDecimal.valueOf(1000)) < 0 ? rebateNowWomen : rebateNowWomen.divide(BigDecimal.valueOf(10));
                                // 多招聘 n 个人  返费 = 单人返费 * n
                                if (manNumNow > ManNum) {
                                    //男 女 多招的人的返费金额
                                    man = man.multiply(BigDecimal.valueOf(finalEditManNum));
                                    women = women.multiply(BigDecimal.valueOf(finalEditWomenNum));
                                    //返费 金额 存到  临时字段
                                    rebaterecordMapper.updateRebateMoney(women.add(man), 1, null);
                                } else {
                                    //钱退到账户余额
                                    //男女 少招的人的返费金额
                                    //少招聘n个人 返费 = 单人返费 * n
                                    man = man.multiply(BigDecimal.valueOf(finalEditManNum1));
                                    women = women.multiply(BigDecimal.valueOf(finalEditWomenNum1));
                                    acctMapper.returnMoney(2, man.add(women));
                                }
                                if (u.getRebateType().equals(0)) {
                                    //面试返佣
                                    rebaterecordMapper.updateRebateMoney(women.add(man), 1, 0);
                                } else if (u.getRebateType().equals(1)) {
                                    //报道返佣
                                    rebaterecordMapper.updateRebateMoney(women.add(man), 1, 1);
                                } else if (u.getRebateType().equals(2)) {
                                    //入职返佣
                                    rebaterecordMapper.updateRebateMoney(women.add(man), 1, 2);
                                }
                                return u;
                            }).collect(Collectors.toList());

                    briefchapterMapper.updateStatus(1);
                    return m;
                }).collect(Collectors.toList());
    }

    @Test
    public void test3() {
        BigDecimal a = BigDecimal.valueOf(900);
        BigDecimal b = BigDecimal.valueOf(4005);


    }
}