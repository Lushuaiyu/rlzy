package com.nado.rlzy.platform.constants;

/**
 * @ClassName
 * @Description TODO
 * @Author ar
 * @Data 2019/5/20 17:27
 * @Version 1.0
 */
public class RlzyConstant {


    public static final String ACCOUNT_BALANCE_IS_EMPTY = "账户余额不足请去充值";

    public static final String PERMISSION = "您没有权限访问该接口";
    /**
     * 失败信息
     */
    public static String ERROR_MSG = "操作失败";

    public static final String PHONE_NULL = "手机号码不能为空";

    public static final String SMS_CODE_STALEDATED = "验证码已过期";

    public static final String PHONE_NOT_LEGITIMATE = "手机号码格式不合法";

    public static final String SMS_MESSAGE_NULL = "短息类型不能为空";

    public static final String CODE_NULL = "验证码不能为空";

    public static final String FEEDBACK_NOT_NULL = "反馈内容不能为空";

    public static final String USERID_NOT_NULL = "用户id不能为空";

    public static final String FEEDBACK_NAME_NOT_NULL = "反馈人不能为空";

    public static final String FEEDBACK_PHONE_NOT_NULL = "反馈人手机号不能为空";

    public static final String SMS_MESSAGE_FAILED = "验证码错误";

    public static final String PASSWORD_SALT = "nado_rlzy";

    public static final String PASSWORD_NULL = "密码不能为空";

    public static final String REGISTER_SUCCESS = "注册成功";

    public static final String REGISTER_FAILED = "注册失败";

    public static final String SMS_MESSAGE_ILLEGALITY = "短信类型违法";

    public static final Integer OPS_SUCCESS_CODE = 0;

    public static final Integer OUT_REGISTER_CODE = 210;

    /**
     * 没有tooken异常 状态码
     */
    public static final Integer NOT_TOKEN_EXCEPTION = 202;

    public static final String NOT_TOKEN_MSG = "无token，请重新登录";

    /**
     * 注册短信类型
     */
    public static final Integer SMS_REGISTER_TYPE = 0;

    /**
     * 提现短信类型
     */
    public static final Integer SMS_WITHDRAW_TYPE = 1;

    /**
     * 修改密码短信类型
     */
    public static final Integer SMS_CHANGE_PASSWORD = 2;

    /**
     * 忘记密码的短信类型
     */
    public static final Integer SMS_FORGET_PASSWORD = 3;


    public static final String OUT_REGISTER_MSG = "该手机号已经注册过";

    public static final String OUT_NO_REGISTER_MSG = "该手机号没有注册过";

    public static final String OPS_SUCCESS_MSG = "操作成功";
    public static final Integer OPS_FAILED_CODE = 300;
    public static final String OPS_FAILED_MSG = "操作失败";

    public static final Integer USER_NOT_LOGIN_CODE = 305;
    public static final String USER_NOT_LOGIN_MSG = "没有登陆";

    public static final String USER_PERMISSIONS = "用户权限";

    public static final String USER_SESSION = "user_session_id";

    public static final String ADD_SUCESSFUL = "添加成功";

    public static final String ADD_FAILED = "添加失败";

    public static final String NULL_PARAM = "参数不能为空";

    public static final String NULLL_USWERNAME = "报名者姓名不能为空";

    public static final String HEAD_IMAGE_NOT_NULL = "头像不能为空";

    public static final String SEX_NOT_NULL = "性别不能不选";

    public static final String EDUCTION_NOT_NULL = "学历不能为空";

    public static final String GRADUATION_TIME = "毕业时间不能为空";

    public static final String PROFESSION = "专业不能为空";

    public static final String REGISTRATION_POSITION_ID = "意向岗位不能为空";

    public static final String ARRIVAL_TIME = "到岗时间不能为空";

    public static final String EXPECTED_SALARY_LOWER = "期望薪资下限";

    public static final String EXPECTED_SALARY_UPPER = "期望工资上限";

    public static final String IT_IS_PUBLIC = "是否公开不能不选";

    public static final String AGREE_PLATFORM_HELP = "是否获取平台帮助";

    /**
     * 短信验证码的 key
     */
    public static final String SMS_CODE = "sms_code";


}