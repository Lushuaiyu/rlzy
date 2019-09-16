package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.MySignUpTableSignUp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface MySignUpTableSignUpMapper extends Mapper<MySignUpTableSignUp>, MySqlMapper<MySignUpTableSignUp> {

    /**
     * 求职端 我的报名表 推荐人添加报名表到分组
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 23:03 2019-09-15
     * @Param [up]
     * @return int
     */
    int insertLiist(@Param("up") List<MySignUpTableSignUp> up);


}