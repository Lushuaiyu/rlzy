package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.db.pojo.HrUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 招聘端 注册登录mapper
 *
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 10:51 2019/6/29
 **/
public interface HrUserMapper extends Mapper<HrUser> {


    int save(HrUser record);

    HrUser finrdUserById(String userId);


    /**
     * 编辑个人资料 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:44 2019/7/9

     * @return int
     **/
    int editPersonData( HrUser user);

    /**
     * 获取手机号码
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:47 2019/6/29
     * @Param [query]
     **/
    HrUser getUserByMobile(Map<String, Object> map);


    String getPassword(@Param("phone") String phone);

    /**
     * 注册
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:01 2019/6/29
     * @param phone 手机号
     **/
    HrUser queryUserByPhone(@Param("phone") String phone);


    /**
     * 登陆
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 16:51 2019-06-29
     * @Param [phone, password]
     */
    HrUser getUserByMobileAndPwd(Map<String, Object> map);

    /**
     * 修改密码
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:39 2019/7/16
     * @Param [userId]
     * @return int
     **/
    int changePassword(@Param("userId") Integer userId, @Param("password") String password);

    /**
     *  切换身份
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:58 2019/7/16
     * @param userId 用户 id
     * @param type 用户身份
     * @return int
     **/
    int switchIdentity(@Param("userId") Integer userId, @Param("type") Integer type);


    /**
     * 招聘端 首页 查询推荐人信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:29 2019/7/25
     * @param userId 推荐人id
     * @param typeId 推荐人 身份id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> selectReferrerInfo(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    /**
     * 查询推荐人 || 求职者名字
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:48 2019/8/4
     * @Param [type, id]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    @Select(value = "select UserName as userName from hr_user where type = #{type} and id = #{userId}")
    List<ComplaintPage> selectMyselfName(@Param("type") Integer type, @Param("userId") Integer userId);

}