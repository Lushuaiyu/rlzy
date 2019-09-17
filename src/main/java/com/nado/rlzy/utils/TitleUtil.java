package com.nado.rlzy.utils;

import com.nado.rlzy.utils.telMessage.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName验证码工具类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-17 11:03
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleUtil {
    private String phone;
    /**
     * 验证码类型
     */
    private String type;
    /**
     * 时间，单位为分钟
     */
    private String time;
    /**
     * 验证码
     */
    private String vaildate;
    /**
     * 发送时间
     */
    private long sendTime;

    @Override
    public boolean equals(Object obj) {
        Title t = (Title) obj;
        if (t.getPhone().equals(this.getPhone()) && t.getType().equals(this.getType()))
            return true;
        return false;
    }
}
