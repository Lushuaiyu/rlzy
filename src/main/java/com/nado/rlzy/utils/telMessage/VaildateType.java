package com.nado.rlzy.utils.telMessage;

import java.util.HashMap;
import java.util.Map;

public class VaildateType {

    public static String register = "5";
    public static String forgetPassword = "5";
    public static String updatePassword = "5";// 注册验证码时间（单位分钟）
    public static String updatePhone = "5";

    public static String registerID = "227147";
    public static String forgetPasswordID = "227149";
    public static String updatePasswordID = "227150";
    public static String updatePhoneID = "229308";
    public static Map<String, String> mapTime = new HashMap<String, String>();
    public static Map<String, String> mapTmodId = new HashMap<String, String>();

    static {
        mapTime.put("register", VaildateType.register);
        mapTime.put("forgetPassword", VaildateType.forgetPassword);
        mapTime.put("updatePassword", VaildateType.updatePassword);
        mapTime.put("updatePhone", VaildateType.updatePhone);

        mapTmodId.put("register", VaildateType.registerID);
        mapTmodId.put("forgetPassword", VaildateType.forgetPasswordID);
        mapTmodId.put("updatePassword", VaildateType.updatePasswordID);
        mapTmodId.put("updatePhone", VaildateType.updatePhoneID);
    }

    public static String getTimeByType(String str) {
        return mapTime.get(str);
    }

    public static String getModelIdByType(String str) {
        return mapTmodId.get(str);
    }
}
