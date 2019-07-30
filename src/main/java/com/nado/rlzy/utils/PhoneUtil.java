package com.nado.rlzy.utils;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.platform.constants.RlzyConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by lsy on 2018/12/7 0007.
 *
 * @author Administrator
 */
public class PhoneUtil {
    public  static void phone(String phone) {
        Assert.isFalse(StringUtils.isBlank(phone), RlzyConstant.PHONE_NULL);
        Assert.isTrue(!(Pattern.matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$",
                phone)), RlzyConstant.PHONE_NOT_LEGITIMATE);

    }

    public static void main(String[] args) {
        phone("13801466261");
    }
}
