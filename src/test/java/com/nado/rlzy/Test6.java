package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/30 13:49
 * @Version 1.0
 */
public class Test6 extends BaseTest {

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Test
    public void test1() {


        //list 存在
        /*if (list.size() > 0) {
            list.stream()
                    .map(dto -> {
                        //入职返佣金额男 简章对应的多笔返佣金额
                        Map<Integer, BigDecimal> rebateMaleEntry = dto.getRebateEntryResignation()
                                .stream()
                                .filter(x -> x.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                        //入职返佣金额女 简章对应的多笔返佣金额
                        Map<Integer, BigDecimal> rebateFemaleEntry = dto.getRebateEntryResignation().stream()
                                .filter(x -> x.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));


                        //声明
                        final BigDecimal[] maleEntry = {null};
                        final BigDecimal[] femaleEntry = {null};
                        rebateFemaleEntry.forEach((k, v) -> {
                            maleEntry[0] = v;
                        });
                        rebateMaleEntry.forEach((k, v) -> {
                            femaleEntry[0] = v;
                        });
                        BigDecimal maleEntryTernaryOperator = maleEntry[0] != null ? maleEntry[0] : BigDecimal.ZERO;
                        BigDecimal femaleEntryTernaryOperator = femaleEntry[0] != null ? femaleEntry[0] : BigDecimal.ZERO;

                        //简章表 简章返佣的钱加起来
                        BigDecimal reMaInterview = dto.getRebateMaleInterview() != null ? dto.getRebateMaleInterview() : BigDecimal.ZERO;
                        BigDecimal reMaReport = dto.getRebateMaleReport() != null ? dto.getRebateMaleReport() : BigDecimal.ZERO;
                        BigDecimal reFemInterview = dto.getRebateFemaleInterview() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                        BigDecimal reFemReport = dto.getRebateFemaleReport() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                        //男生返佣的钱
                        BigDecimal allRebateManMoney = maleEntryTernaryOperator.add(reMaInterview)
                                .add(reMaReport);
                        BigDecimal allRebateWomenMoney = femaleEntryTernaryOperator.add(reFemInterview)
                                .add(reFemReport);
                        //招聘人数（可增可减，减的话，钱从冻结金额退到到账户余额, 增的话 钱从账户余额到冻结金额）
                        //发布简章时的人数
                        Integer manNum = dto.getManNum();
                        Integer womenNum = dto.getWomenNum();
                        //编辑简章时的人数
                        Integer manNumNow = query.getManNumNow();
                        Integer womenNumNow = query.getWomenNumNow();

                        // 编辑简章时的返佣金额
                        BigDecimal reMaleInterview = query.getRebateMaleInterview() != null ? query.getRebateMaleInterview() : BigDecimal.ZERO;
                        BigDecimal reMaleReport = query.getRebateMaleReport() != null ? query.getRebateMaleReport() : BigDecimal.ZERO;
                        BigDecimal reFemaleInterview = query.getRebateFemaleInterview() != null ? query.getRebateFemaleInterview() : BigDecimal.ZERO;
                        BigDecimal reFemaleReport = query.getRebateFemaleReport() != null ? query.getRebateFemaleReport() : BigDecimal.ZERO;

                        boolean size = dto.getRebateEntryResignation().size() > 0;
                        if (reMaleInterview.equals(0) && reMaleReport.equals(0) &&
                                reFemaleInterview.equals(0) && reFemaleReport.equals(0) && size == false) {
                            //招聘人数（可增可减，减的话，钱从冻结金额退到到账户余额)
                            //招聘人数减少 manNum - manNuimNow > 0 返佣金额没变
                            if (manNum - manNumNow > 0) {
                                int manNumOne = manNum - manNumNow;
                                // n个人的所有返佣 n * 单人返佣
                                BigDecimal allRebateMan = allRebateManMoney.multiply(BigDecimal.valueOf(manNumOne));
                                acctMapper.reimbursement(query.getUserId(), allRebateMan);
                            }
                            //招聘人数（可增可减，减的话，钱从冻结金额退到到账户余额) 返佣金额没变
                            if (womenNum - womenNumNow > 0) {
                                int womenNumOne = womenNum - womenNumNow;
                                BigDecimal allRebateWomen = allRebateWomenMoney.multiply(BigDecimal.valueOf(womenNumOne));
                                acctMapper.reimbursement(query.getUserId(), allRebateWomen);
                            }
                            //招聘人数增加 返佣金额没变 男
                            if (manNumNow - manNum > 0) {
                                int manNumTwo = manNumNow - manNum;
                                BigDecimal allRebateMan = allRebateManMoney.multiply(BigDecimal.valueOf(manNumTwo));
                                acctMapper.reimbursementAddNum(query.getUserId(), allRebateMan);
                            }
                            //招聘人数增加 女
                            if (womenNumNow - womenNum > 0) {
                                int womenNumTwo = womenNumNow - womenNum;
                                BigDecimal allRebateWomen = allRebateWomenMoney.multiply(BigDecimal.valueOf(womenNumTwo));
                                acctMapper.reimbursementAddNum(query.getUserId(), allRebateWomen);
                            }
                            //修改招聘人数
                            mapper.updateRebateMoney(query);
                        } else {
                            //修改招聘人数和返佣
                            //招聘人数增加 返佣增加
                            int manNumOne = manNumNow - manNum > 0 ? manNumNow - manNum : 0;
                            int womenNumOne = womenNumNow - womenNum > 0 ? womenNumNow - womenNum : 0;
                            //相减 > 0 就说明改了
                            BigDecimal editReMaleInterview = reMaleInterview.subtract(reMaInterview);
                            BigDecimal editReMaleReport = reMaleReport.subtract(reMaReport);
                            if (query.getResignations().size() > 0) {
                                //入职返佣金额男 简章对应的多笔返佣金额 编辑简章时前台传过来
                                Map<Integer, BigDecimal> queryRebateMaleEntry = query.getResignations()
                                        .stream()
                                        .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                                CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                                //入职返佣金额女 简章对应的多笔返佣金额 编辑简章时前台传过来
                                Map<Integer, BigDecimal> queryRebateFemaleEntry = query.getResignations().stream()
                                        .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                                CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                            }
                            final BigDecimal[] queryMaleEntry = {null};
                            final BigDecimal[] queryFemaleEntry = {null};
                            rebateFemaleEntry.forEach((k, v) -> {
                                queryMaleEntry[0] = v;
                            });
                            rebateMaleEntry.forEach((k, v) -> {
                                queryFemaleEntry[0] = v;
                            });

                            //招聘人数增加 返佣增加
                            int manNumThree = manNum - manNumNow > 0 ? manNum - manNumNow : 0;
                            int womenNumThree = womenNum - womenNumNow > 0 ? womenNum - womenNumNow : 0;
                            //判断 招聘人数 和返佣 至少都改了一个

                            boolean rebateFalg = (manNumOne > 0 || womenNumOne > 0) && ((!editReMaleInterview.equals(0)) ||
                                    (!editReMaleReport.equals(0)) || (dto.getRebateEntryResignation().size() > 0));

                            //招聘人数减 返佣加
                            boolean rebateNoSubtract = (manNumThree > 0 || womenNumThree > 0) && ((!editReMaleInterview.equals(0)) ||
                                    (!editReMaleReport.equals(0)) || (dto.getRebateEntryResignation().size() > 0));
                            //招聘人数 增加 和返佣 增加  至少都改了一个 并且 入职返佣 可能删除
                            if (rebateFalg == true) {
                                // 返佣金额改变时 发布简章的人数 * 返佣金额的差值 + (修改简章人数 - 发布简章人数) * 返佣金额 这笔钱从账户余额
                                // 到冻结金额
                                //面试金额男的差值
                                BigDecimal subtract = reMaleInterview.subtract(reMaInterview);
                                //报道金额男的差值
                                BigDecimal subtract1 = reMaleReport.subtract(reMaReport);
                                //面试金额女差值
                                BigDecimal subtract4 = reFemaleInterview.subtract(reFemInterview);
                                //报道金额女差值
                                BigDecimal subtract5 = reFemaleReport.subtract(reFemReport);
                                //入职返佣男 差值
                                BigDecimal subtract2 = queryMaleEntry[0].subtract(maleEntry[0]);
                                //入职返佣女 差值
                                BigDecimal subtract3 = queryFemaleEntry[0].subtract(femaleEntry[0]);
                                //返佣男 差值之和
                                BigDecimal maleDifference = subtract.add(subtract1).add(subtract2);
                                //返佣女 差值之和
                                BigDecimal femaleDifference = subtract4.add(subtract5).add(subtract3);
                                // 编辑简章时的返佣金额 和 男
                                BigDecimal queryRebateAddMale = reMaInterview.add(reMaleReport).add(queryMaleEntry[0]);
                                //编辑简章时的返佣金额 和 女
                                BigDecimal queryRebateAddFemale = reFemaleInterview.add(reFemaleReport).add(queryFemaleEntry[0]);

                                //招聘人数 增加 和返佣 增加 时的所有男返费金额
                                BigDecimal multiply = maleDifference.multiply(BigDecimal.valueOf(manNum)).add(BigDecimal.valueOf(manNumNow - manNum)).multiply(queryRebateAddMale);
                                //招聘人数 增加 和返佣 增加 时的所有女返费金额
                                BigDecimal multiply1 = femaleDifference.multiply(BigDecimal.valueOf(womenNum)).add(BigDecimal.valueOf(womenNumNow - womenNum)).multiply(queryRebateAddFemale);

                                //钱从账户余额到招聘金额
                                acctMapper.reimbursementAddNum(query.getUserId(), multiply.add(multiply1));
                                //更改招聘人数和返佣的钱 简章表
                                mapper.updateRebateMoney(query);
                                //更改 entry_resignation 表的信息
                                resignationMapper.resignationEntry(query);
                                //删除 入职返佣
                                //resignationMapper.deleteEntryRebate(query.getResignations());

                            } else if (rebateNoSubtract == true) {
                                //招聘人数减少 返佣增加
                                // (发布简章人数 - 编辑简章人数 * 发布简章返佣金额) 这个是冻结金额 到账户余额
                                // 编辑简章人数 * 返佣差价 这个是 账户金额 到冻结金额

                                int subtractNoMale = manNum - manNumNow;
                                int subtractNoFemale = womenNum - womenNumNow;


                            }


                        }


                        return dto;
                    }).collect(Collectors.toList());*/

    }


    @Test
    public void test2() {
        //人数增加 返佣金额增加 (编辑返佣 - 发布简章返佣) = 增加的钱
        //
        //发布简章的返佣
        BigDecimal bigDecimal = new BigDecimal(30);
        BigDecimal bigDecimal2 = new BigDecimal(10);
        BigDecimal bigDecimal3 = new BigDecimal(20);
        //编辑简章的返佣
        BigDecimal bigDecimal4 = new BigDecimal(30);
        BigDecimal bigDecimal5 = new BigDecimal(10);
        BigDecimal bigDecimal6 = new BigDecimal(20);

        //发布返佣 男
        BigDecimal add = bigDecimal.add(bigDecimal2).add(bigDecimal3) != null ? bigDecimal.add(bigDecimal2).add(bigDecimal3) : BigDecimal.ZERO;
        //编辑返佣 男
        BigDecimal add1 = bigDecimal4.add(bigDecimal5).add(bigDecimal6) != null ? bigDecimal4.add(bigDecimal5).add(bigDecimal6) : BigDecimal.ZERO;
        //差价 男
        BigDecimal subtract = add1.subtract(add);
        //发布简章 男
        Integer num1 = 10;
        //女
        Integer num2 = 10;
        //编辑简章 男
        Integer num3 = 30;
        //女
        Integer num4 = 30;
        // 30 * 20 + 10*  20
        BigDecimal multiply1 = bigDecimal.multiply(BigDecimal.valueOf(num3 - num1)).add(bigDecimal2).multiply(BigDecimal.valueOf(num4 - num2));
        System.out.println(multiply1);

        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(20)).add(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(20)));
        BigDecimal multiply = bigDecimal.multiply(BigDecimal.valueOf(20));
        System.out.println(multiply);
        BigDecimal multiply2 = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(20));
        System.out.println(multiply2);
        System.out.println(multiply.add(multiply2));

        //编辑简章
        // 后男生返佣的钱
        //(编辑返佣男 * 男生增加的人数) + 返佣差价 * 发布简章的人数 这笔钱 账户余额 到 冻结金额
        //BigDecimal multiply = add1.multiply(BigDecimal.valueOf(i)).add(subtract).multiply(BigDecimal.valueOf(num1));


    }

    @Test
    public void test5(){
        HrRebaterecord rebaterecord = new HrRebaterecord();
        rebaterecord.setRebateMale(BigDecimal.valueOf(44));
        rebaterecord.setRebateFemale(BigDecimal.valueOf(543));
        rebaterecord.setRebateTime(new Date());
        rebaterecord.setBriefchapterId(4);
        rebaterecord.setRebateType(7);
        HrRebaterecord rebaterecord2 = new HrRebaterecord();
        rebaterecord2.setRebateMale(BigDecimal.valueOf(44));
        rebaterecord2.setRebateFemale(BigDecimal.valueOf(543));
        rebaterecord2.setRebateTime(new Date());
        rebaterecord2.setBriefchapterId(4);
        rebaterecord2.setRebateType(7);
        HrRebaterecord rebaterecord3 = new HrRebaterecord();
        rebaterecord3.setRebateMale(BigDecimal.valueOf(44));
        rebaterecord3.setRebateFemale(BigDecimal.valueOf(543));
        rebaterecord3.setRebateTime(new Date());
        rebaterecord3.setBriefchapterId(4);
        rebaterecord3.setRebateType(7);
        HrRebaterecord rebaterecord4 = new HrRebaterecord();
        rebaterecord4.setRebateMale(BigDecimal.valueOf(44));
        rebaterecord4.setRebateFemale(BigDecimal.valueOf(543));
        rebaterecord4.setRebateTime(new Date());
        rebaterecord4.setBriefchapterId(4);
        rebaterecord4.setRebateType(7);
        ArrayList<HrRebaterecord> rebaterecords = new ArrayList<>();
        rebaterecords.add(rebaterecord);
        rebaterecords.add(rebaterecord2);
        rebaterecords.add(rebaterecord3);
        rebaterecords.add(rebaterecord4);

        rebaterecordMapper.selectByPrimaryK(1);


    }
    @Test
    public void test6(){
        JobListQuery query = new JobListQuery();
        query.setSex(0);
        System.out.println(signUpMapper.selectJobListOverview(query));


    }


}