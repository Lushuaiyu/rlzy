package com.nado.rlzy.utils;

import com.nado.rlzy.platform.constants.RlzyConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsy on 2018/12/7 0007.
 *
 * @author Administrator
 */
public class PhoneUtil {
    public static void phone(String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(phone), RlzyConstant.PHONE_NULL);
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        AssertUtil.isTrue(phone.length() != 11, "手机号应为11位数");
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        AssertUtil.isTrue(isMatch == false, RlzyConstant.PHONE_NOT_LEGITIMATE);
    }

    public static void main(String[] args) {
        phone("18620956947");


    }
}
