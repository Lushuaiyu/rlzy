package com.nado.rlzy.utils.telMessage;

import com.nado.rlzy.utils.DateUtil;

public class TitleDateUtil {

    public static Title getTitle(String phone, String dateCreated, String type, String vaildate, String time) {
        Title title = new Title();
        title.setPhone(phone);
        title.setSendTime(DateUtil.getVailTimeToLong(dateCreated));
        title.setType(type);
        title.setVaildate(vaildate);
        title.setTime(time);
        return title;
    }
}