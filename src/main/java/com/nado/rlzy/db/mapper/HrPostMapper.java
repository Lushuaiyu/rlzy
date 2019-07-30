package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrPost;

import java.util.List;

public interface HrPostMapper {


    int insertSelective(ReleaseBriefcharpterQuery query);

    HrPost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrPost record);


    /**
     * 查询职位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:33 2019/7/8
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrPost>
     **/
    List<HrPost> selectPostNameByPost();
}