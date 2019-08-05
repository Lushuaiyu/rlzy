package com.nado.rlzy.service;

import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.pojo.Feedback;
import com.nado.rlzy.db.pojo.HrSignUp;
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
     * 查询我的企业 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:48 2019/7/1
     * @Param [status] 状态
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     **/
    Map<String, Object> queryPersonCo(Integer userId);


    /**
     * 添加企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:46 2019/7/1
     * @Param []
     * @return void
     **/
    void addCo(AddCoQuery query, String url) throws ImgException;



    /**
     * 图片上传
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:11 2019/7/1
     * @Param [file]
     * @return java.lang.String
     **/
    String updateHead(MultipartFile file)throws ImgException;


    /**
     * 招聘端个人资料 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:32 2019/7/9
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<HrSignUp> personalInformation(Integer userId);

    /**
     * 招聘端个人资料 推荐人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:13 2019/7/9
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> personalInformationReferrer(Integer userId);



    /**
     * 编辑资料 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:12 2019/7/9
     * @return void
     **/
    void editPersonData(EditPersonDataQuery query, String url);

    /**
     * 编辑资料 推荐人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:20 2019/7/9
     * @Param [query]
     * @return void
     **/
    void editPersonDataRecommend(EditPersonDataQuery query);

    /**
     * 查询投诉记录
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:31 2019/7/10
     * @param userId 用户id
     * @param typeId 类型id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    HashMap<Object, Object> searchComplaintRecord(Integer userId, Integer typeId);

    /**
     * 查看投诉详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:50 2019/7/10
     * @param complaintId 投诉记录id
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> complaint(Integer complaintId);

    /**
     * 招聘端 首页 推荐人信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:37 2019/7/25
     * @Param [userId, typeId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> selectReferrerInfo(Integer userId, Integer typeId);

    /**
     * 招聘端 求知与反馈
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:38 2019/7/25
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.Feedback>
     **/
    List<Feedback> feedback();

    /**
     * 我的反馈
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:59 2019/7/25
     * @Param [content, userId, name, phone]
     * @return int
     **/
    int myFeedback (String content,  Integer userId, String name, String phone);









}