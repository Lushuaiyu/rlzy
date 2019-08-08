package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.MySignUpTable;
import org.apache.ibatis.annotations.Param;

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
     * 查询我的求职表自定义分组 推荐人身份才能添加分组
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/7/22
     * @param userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    List<MySignUpTable> searchGroupingInformation(@Param("userId") Integer userId);

    /**
     * 查询我的求职表默认分组
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:50 2019/8/8
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    List<MySignUpTable> searchGroupingdefault();

    /**
     * 新增报名表主键id 放到 我的分组里面
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:46 2019/8/8
     * @Param [signUpId]
     * @return int
     **/
    int updateMySignUpTableSignId(@Param("signUpId") Integer signUpId);








}