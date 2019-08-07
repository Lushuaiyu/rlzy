package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.PersonCoDto;
import com.nado.rlzy.db.pojo.HrGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author lushuaiyu
 */
public interface HrGroupMapper extends Mapper<HrGroup> {


    /**
     * 公司主页
     *
     * @param groupId 代招单位 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:13 2019/7/10
     **/
    List<HrGroup> coHomePage(@Param("groupId") Integer groupId);

    /**
     * 查询企业名字 招聘企业 代招企业
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/8/1
     * @Param [type, userId]
     **/
    List<HrGroup> selectGroupName(@Param("type") Integer type, @Param("userId") Integer userId);

    /**
     * 查询代招单位的pid
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:37 2019/8/6
     * @Param [type, userId]
     **/
    List<HrGroup> queryAgentEnterprisePid(@Param("userId") Integer userId);


    /**
     * 查询个人企业 代招单位
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:37 2019/7/3
     * @Param status 企业状态
     **/
    List<PersonCoDto> queryPersonCo(@Param("userId") Integer userId);

    /**
     * 查询个人企业 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:56 2019/8/3
     * @Param [userId]
     **/
    List<PersonCoDto> queryPersonCORecruitment(Integer userId);

    /**
     * 认证失败说明
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:18 2019/8/7
     * @param userId 用户id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/

    Map<String, List<PersonCoDto>> queryTheAuditFailed(@Param("userId") Integer userId);


}