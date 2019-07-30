package com.nado.rlzy.utils.telMessage;


public class Title {

    private String phone;
    private String type;// 验证码类型
    private String time;// 时间，单位为分钟
    private String vaildate;// 验证码
    private long sendTime;// 发送时间

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVaildate() {
        return vaildate;
    }

    public void setVaildate(String vaildate) {
        this.vaildate = vaildate;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public boolean equals_2(Title t) {
        if (this.getPhone().equals(t.getPhone()) && this.getType().equals(t.getType()))
            return true;
        return false;

    }

    @Override
    public String toString() {
        return "Title [phone=" + phone + ", type=" + type + ", time=" + time + ", vaildate=" + vaildate + ", sendTime="
                + sendTime + "]";
    }

    public boolean equals(Object obj) {
        Title t = (Title) obj;
        if (t.getPhone().equals(this.getPhone()) && t.getType().equals(this.getType()))
            return true;
        return false;
    }

}
