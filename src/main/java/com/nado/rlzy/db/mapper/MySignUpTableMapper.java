package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.MySignUpTable;

import java.util.List;

public interface MySignUpTableMapper {


    /**
     * 添加自定义分组
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:01 2019/7/8
     * @Param record 我的分组对象
     * @return int
     **/
    int insertSelective(MySignUpTable record);

    MySignUpTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MySignUpTable record);


    /**
     * 查询我的求职表 分组信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/7/22
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    List<MySignUpTable> searchGroupingInformation();








}