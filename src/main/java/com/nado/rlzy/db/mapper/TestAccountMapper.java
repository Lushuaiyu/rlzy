package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.TestAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAccountMapper {


    int insertSelective(TestAccount record);

    TestAccount selectById( );

    int updateByPrimaryKeySelective( );

}