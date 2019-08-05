package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.HrGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lushuaiyu
 */
public interface HrGroupMapper extends Mapper<HrGroup> {




    /**
     * 公司主页
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:13 2019/7/10
     * @param groupId 代招单位 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrGroup> coHomePage(@Param("groupId") Integer groupId);

    /**
     * 查询企业名字 招聘企业 代招企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/8/1
     * @Param [type, userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     **/
    List<HrGroup> selectGroupName(@Param("type") Integer type, @Param("userId") Integer userId);




}