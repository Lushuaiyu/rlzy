package com.nado.rlzy.db.mapper;

import com.nado.rlzy.base.BaseMapper;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.db.pojo.HrComplaint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lushuaiyu
 */
public interface HrComplaintMapper extends BaseMapper<HrComplaint> {

    /**
     * 查询投诉记录 投诉简章为代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:24 2019/7/10
     * @Param userId 用户id
     * @param typeId 类型id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> searchComplaintRecord(@Param("userId") Integer userId,
                                             @Param("typeId") Integer typeId);

    /**
     * 查询投诉记录 投诉简章为  招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:57 2019/8/4
     * @Param [userId, typeId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> searchComplaintRecordMyself(@Param("userId") Integer userId,
                                                   @Param("typeId") Integer typeId);


    /**
     * 查看投诉详情 投诉公司是代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:49 2019/7/10
     * @param complaintId 投诉记录id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> complaint(@Param("complaintId") Integer complaintId);

    /**
     * 查看投诉详情 投诉公司是招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:09 2019/8/4
     * @Param [complaintId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> complaintRecruitment(@Param("complaintId") Integer complaintId);

    /**
     * 求职端 首页 投诉 查询投诉 废弃
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:58 2019/7/10
     * @Param []
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> complaintStart(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param("briefchapterId") Integer briefchapterId);

    /**
     * 招聘端 信用中心 投诉待处理 已撤销
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:32 2019/7/25
     * @Param [status]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> creditCenter(@Param("status") Integer status);

    /**
     * 查询所有参数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:06 2019/7/25
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrComplaint>
     **/
    List<HrComplaint> queryParams();

    /**
     * 7天内 求职者没有撤销投诉, 平台后台处理
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:38 2019/7/25
     * @Param [complaintId]
     * @return int
     **/
    int updateStatusById(@Param("complaintId") Integer complaintId);







}