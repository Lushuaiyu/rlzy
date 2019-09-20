package com.nado.rlzy.service;

import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.pojo.Feedback;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.exception.ImgException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘端 个人中心service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 14:46
 * @Version 1.0
 */
public interface PersonCenterService {

    /**
     * 查询我的企业
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:48 2019/7/1
     * @Param [status] 状态
     **/
    List<HrGroup> queryPersonCo(Integer userId);


    /**
     * 添加企业 招聘端 代招单位 添加企业 | 认证失败说明
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:46 2019/7/1
     * @Param []
     **/
    Map<String, Object> addCo(AddCoQuery query) throws ImgException;


    /**
     * 图片上传 暂时用不到
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:11 2019/7/1
     * @Param [file]
     **/
    String updateHead(MultipartFile file) throws ImgException;


    /**
     * 求职端 本人个人资料
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:32 2019/7/9
     * @Param [userId]
     **/
    HrUser personalInformation(Integer userId);


    /**
     * 求职端 推荐人个人资料
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:13 2019/7/9
     * @Param [userId]
     **/
    HrUser personalInformationReferrer(Integer userId);


    /**
     * 编辑资料 本人
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:12 2019/7/9
     **/
    void editPersonData(EditPersonDataQuery query);

    /**
     * 编辑资料 推荐人
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:20 2019/7/9
     * @Param [query]
     **/
    void editPersonDataRecommend(EditPersonDataQuery query);

    /**
     * 查询投诉记录
     *
     * @param userId 用户id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:31 2019/7/10
     **/
    HashMap<Object, Object> searchComplaintRecord(Integer userId);

    /**
     * 求职端 信用中心 查看投诉详情 & 撤销投诉
     *
     * @param complaintId 投诉记录id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:50 2019/7/10
     **/
    HashMap<String, Object> complaint(Integer complaintId, Integer type);

    /**
     * 招聘端 首页 推荐人信息
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:37 2019/7/25
     * @Param [userId, typeId]
     **/
    List<HrUser> selectReferrerInfo(Integer userId, Integer typeId);

    /**
     * 招聘端 & 求职端 帮助与反馈
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.Feedback>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:38 2019/7/25
     * @Param []
     **/
    List<Feedback> feedback();

    /**
     * 招聘端 & 求职端 我的反馈
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:59 2019/7/25
     * @Param [content, userId, name, phone]
     **/
    int myFeedback(String content, Integer userId, String name, String phone);

    /**
     * 招聘端个人中心 我的搜藏 推荐人信息概览
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:02 2019/8/16
     * @Param [userId]
     **/
    List<HrUser> collectReferrer(Integer userId);

    /**
     * 招聘端 个人中心 修改昵称和头像
     *
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 15:55 2019-09-17
     * @Param [userId, headImage, userName, type]
     */
    int updateHeadImage(String userId, String headImage, String userName, Integer type);

    /**
     * 查询头像 昵称 身份证 if 子账号 不显示 idCard
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:36 2019/9/18
     * @param userId 用户id
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    Map<String, Object> selectHeadUserNameIdCard(Integer userId);

    /**
     * 子账号负责的企业
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 22:50 2019-09-18
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     */
    List<HrGroup> subAccountCompany(Integer userId);

    /**
     * 查询身份
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:28 2019/9/19
     * @Param [userId]
     * @return int
     **/
    Integer checkUserIdentity(Integer userId);


}