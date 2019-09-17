package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.TestAccountMapper;
import com.nado.rlzy.db.pojo.test.TestAccount;
import com.nado.rlzy.service.TestAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @ClassName 模拟转账 实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/5 10:51
 * @Version 1.0
 */
@Service
public class TestAccountServiceImpl implements TestAccountService {

    @Resource
    private TestAccountMapper mapper;



    @Override
    public TestAccount getAccount( ) {
        return mapper.selectById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMoney() throws Exception {
        mapper.updateByPrimaryKeySelective();
        throw new SQLException("发生异常了");


    }


    public static int [] sort (int [] source){
        int[] arr = Arrays.copyOf(source, source.length);
        for (int i = 1; i < arr.length; i++){

            int temp = arr[i];
            int j = i;
            while (j > 0 && temp < arr[j - 1]){
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = temp;
            }
        }
        return arr;
    }


    public static void main(String[] args) {




    }




}