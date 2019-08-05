package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Collect;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CollectMapper extends Mapper<Collect> {


    /**
     *  添加收藏
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