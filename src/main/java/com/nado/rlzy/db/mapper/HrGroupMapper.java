package com.nado.rlzy.db.mapper;

import com.nado.rlzy.base.BaseMapper;
import com.nado.rlzy.db.pojo.HrGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HrGroupMapper extends BaseMapper<HrGroup>, Mapper<HrGroup> {




    /**
     * 公司主页
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:13 2019/7/10
     * @param groupId 代招单位 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrGroup> coHomePage(@Param("groupId") Integer groupId);

}