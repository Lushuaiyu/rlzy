package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface MessageMapper extends Mapper<Message>, MySqlMapper<Message> {

    /**
     * 身份是本人登录的 代招单位的面试通知
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:15 2019-09-06
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */
    List<Message> noticeMyselfRepresentativeUnit(@Param("userId") Integer userId);

    /**
     * 身份是本人登录的 招聘单位的面试通知
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:19 2019-09-06
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */
    List<Message> noticeMyselfRecruitmentUnit(@Param("userId") Integer userId);

    /**
     * 推荐人登录 代招单位的面试通知
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:20 2019-09-06
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */
    List<Message> noticeReferrerRepresentativeUnit(@Param("userId") Integer userId);

    /**
     * 推荐人登录 招聘单位的面试通知
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:37 2019-09-06
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */
    List<Message> noticeReferrerRecruitmentUnit(@Param("userId") Integer userId);

    /**
     * 查询推荐人的所有记录
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 18:49 2019-09-07
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.Message>
     */

    List<Message> selectAllList();
}