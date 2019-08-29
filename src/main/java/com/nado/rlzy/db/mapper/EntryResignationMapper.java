package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.db.pojo.EntryResignation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface EntryResignationMapper extends Mapper<EntryResignation>, MySqlMapper<EntryResignation> {
    /**
     * 根据简章id查询
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:02 2019/8/27
     * @Param [briefchapter]
     * @return java.util.List<com.nado.rlzy.db.pojo.EntryResignation>
     **/
    List<EntryResignation> selectByAll(@Param("briefchapter") Integer briefchapter);

    /**
     * 修改发布简章时的返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:30 2019/8/28
     * @Param [query]
     * @return int
     **/
    int resignationEntry(EditBriefchapterQuery query);

    /**
     * 编辑简章时查询入职返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:55 2019/8/28
     * @Param [briefchapterId]
     * @return java.util.List<com.nado.rlzy.db.pojo.EntryResignation>
     **/
    List<EntryResignation> selectEntryRebate(@Param("briefchapterId") Integer briefchapterId);

}