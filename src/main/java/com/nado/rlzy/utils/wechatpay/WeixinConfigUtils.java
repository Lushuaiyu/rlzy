package com.nado.rlzy.utils.wechatpay;

import com.nado.rlzy.utils.alipay.AlipayConfig;


@SuppressWarnings("unused")
public class WeixinConfigUtils {

    public final static String appid;
    public final static String mch_id;
    public final static String appSecret;
    public final static String notify_url;
    public final static String Key;
    public final static String ip;

    static {
        //APP ID
        appid = "wx626b8ee2634decb5";
        //商户ID
        mch_id = "1494141492";
        //回调接口
        notify_url = AlipayConfig.realmname + "/yunzhixue/order/weixin_notify.do";

        appSecret = "85d8fa421160d61c635efd4d43e87249";

        Key = "465sad5cfb54gn78gj8775232xsa1415";
        //服务器IP
        ip = "116.62.118.146";
    }
}
