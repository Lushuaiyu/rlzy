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
     * 求职端公司主页基本信息 代招单位 || 招聘单位
     *
     * @param groupId 代招单位 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:13 2019/7/10
     **/
    List<HrGroup> coHomePage(@Param("groupId") Integer groupId);

    /**
     * 求职端公司主页上面信息 代招单位 || 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 18:03 2019-09-15
     * @Param [groupId]
     */
    List<HrGroup> coHomePageUpward(@Param("groupId") Integer groupId);

    /**
     * 招聘端 发布简章 查询被招聘企业的名字
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/8/1
     * @Param [type, userId]
     **/
    List<HrGroup> selectGroupName(@Param("type") Integer type, @Param("userId") Integer userId);

    /**
     * 招聘端 发布简章 查询招聘企业的名字
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:56 2019/8/14
     * @Param [type, userId]
     **/
    List<HrGroup> selectGroupNameRecruitment(@Param("type") Integer type, @Param("userId") Integer userId);

    /**
     * 查询代招单位的id 这个id是代招单位下的单位的pid
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:37 2019/8/6
     * @Param [type, userId]
     **/
    HrGroup queryAgentEnterprisePid(@Param("userId") Integer userId);


    /**
     * 查询个人企业 代招单位 | 代招单位
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:37 2019/7/3
     * @Param status 企业状态
     **/
    List<HrGroup> queryPersonCo(@Param("userId") Integer userId);


    /**
     * 认证失败说明
     *
     * @param groupId id
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:18 2019/8/7
     **/
    List<HrGroup> queryTheAuditFailed(@Param("groupId") Integer groupId);

    /**
     * 子账号负责的企业
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:35 2019-09-18
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     */
    List<HrGroup> subAccountCompany(@Param("userId") Integer userId);

    /**
     * 查询企业认证状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:49 2019/9/19
     * @param userId  user id
     * @param type user type
     * @return com.nado.rlzy.db.pojo.HrGroup
     **/
    HrGroup selectCoCertificationStatus(@Param("userId") Integer userId, @Param("type") Integer type);


}