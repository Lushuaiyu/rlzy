package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionsMapper {


    int insertSelective(Options record);

    Options selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Options record);

    /**
     * 根据type 查id 和内容 前端选项内容
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:11 2019/7/8
     * @param type 类型
     * @return
     **/
    List<Options> selectContentByType(@Param("type") Integer type);

}