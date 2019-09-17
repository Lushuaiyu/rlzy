package com.nado.rlzy.db.mapper;

import com.nado.rlzy.base.BaseMapper;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.db.pojo.HrComplaint;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author lushuaiyu
 */
public interface HrComplaintMapper extends BaseMapper<HrComplaint>, Mapper<HrComplaint>, MySqlMapper<HrComplaint> {

    /**
     * 求职端 个人中心 信用中心 查询投诉记录 投诉简章为代招单位下的单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:24 2019/7/10
     * @Param userId 用户id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> searchComplaintRecord(@Param("userId") Integer userId);

    /**
     * 求职端 查询投诉记录 投诉简章为  招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:57 2019/8/4
     * @Param [userId, typeId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> searchComplaintRecordMyself(@Param("userId") Integer userId);


    /**
     * 查看投诉详情 投诉公司是代招单位下的单位
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
     * 招聘端 信用中心 投诉待处理 已撤销
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:32 2019/7/25
     * @Param [status]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> creditCenter(@Param("status") List<Integer> status, @Param("userId") Integer userId);

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

    /**
     * 招聘端 个人中心 信用中心 查看投诉
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:56 2019/8/16
     * @Param [brId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrComplaint>
     **/
    List<HrComplaint> selectComplaint(@Param("coId") Integer coId);

    /**
     * 招聘端 个人中心 信用中心 处理投诉
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:23 2019/8/16
     * @Param [comId]
     * @return int
     **/
    int processedToProcessed(@Param("comId") Integer comId);

    /**
     * 撤销投诉
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:03 2019/8/18
     * @Param [query]
     * @return int
     **/
    int updateCom(ComplaintQuery query);

    /**
     * 公司主页 - 历史记录 - 违规记录 代招单位 || 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:58 2019/8/24
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrComplaint>
     **/
    List<HrComplaint> violationRecord(@Param("groupId") Integer groupId);







}