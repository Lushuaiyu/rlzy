package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.TestAccountMapper;
import com.nado.rlzy.db.pojo.TestAccount;
import com.nado.rlzy.service.TestAccountService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Autowired
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

    public static void main(String[] args) {
        /*int [] a = {5,7,3,8,9,1,6};
        System.out.println(Arrays.toString(sort(a)));*/


        var a = "33";

        var list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        System.out.println(Arrays.asList(list));

        System.out.println(a);

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


}