package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Collect;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectMapper {


    /**
     *  招聘端 添加收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:47 2019/7/8
     * @Param [record]
     * @return int
     **/
    int addBriefchapter(Collect record);

    /**
     * 取消收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:47 2019/7/8
     * @Param
     * @return
     **/
    int updateByPrimaryKeySelective(Collect record);




}