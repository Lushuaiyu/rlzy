package com.nado.rlzy.utils.wechatpay;

import java.net.InetAddress;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * 微信APP支付
 */
public class WeixinPayUtil {


    /**
     * 获取微信APP支付需要的数据包，包含在map中外部获取
     *
     * @param orderId
     * @param money
     * @param body
     * @param detail
     * @return
     */
    @SuppressWarnings("static-access")
    public static Map<String, String> createWeiPackage(String orderId, int money, String body, String detail) {
        String appid = WeixinConfigUtils.appid;
        String mch_id = WeixinConfigUtils.mch_id;
        String nonce_str = RandCharsUtils.getRandomString(32);
        String attach = "test";
        String out_trade_no = orderId;
        int total_fee = money;//单位是分，即是0.01元 money
        String spbill_create_ip = WeixinConfigUtils.ip;
        String time_start = RandCharsUtils.timeStart();
        String time_expire = RandCharsUtils.timeExpire();
        String notify_url = WeixinConfigUtils.notify_url;
        String trade_type = "APP";
        //参数：开始生成签名
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("body", body);
        parameters.put("nonce_str", nonce_str);
        parameters.put("detail", detail);
        parameters.put("attach", attach);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("total_fee", total_fee);
        parameters.put("time_start", time_start);
        parameters.put("time_expire", time_expire);
        parameters.put("notify_url", notify_url);
        parameters.put("trade_type", trade_type);
        parameters.put("spbill_create_ip", spbill_create_ip);
        String sign = WXSignUtils.createSign("UTF-8", parameters);
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(appid);
        unifiedorder.setMch_id(mch_id);
        unifiedorder.setNonce_str(nonce_str);
        unifiedorder.setSign(sign);
        unifiedorder.setBody(body);
        unifiedorder.setDetail(detail);
        unifiedorder.setAttach(attach);
        unifiedorder.setOut_trade_no(out_trade_no);
        unifiedorder.setTotal_fee(total_fee);
        unifiedorder.setSpbill_create_ip(spbill_create_ip);
        unifiedorder.setTime_start(time_start);
        unifiedorder.setTime_expire(time_expire);
        unifiedorder.setNotify_url(notify_url);
        unifiedorder.setTrade_type(trade_type);
        //构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        long currentTimeMillis = System.currentTimeMillis();// 生成时间戳
        long second = currentTimeMillis / 1000L;
        String seconds = String.valueOf(second).substring(0, 10);
        Map<String, String> map = ParseXMLUtils.jdomParseXml(weixinPost);
        String prepayid = map.get("prepay_id");
        String partnerid = WeixinConfigUtils.mch_id;
        SortedMap<Object, Object> signParam = new TreeMap<Object, Object>();
        String noncestr = RandCharsUtils.getRandomString(32);
        signParam.put("appid", appid);//app_id
        signParam.put("noncestr", noncestr);//自定义不重复的长度不长于32位
        signParam.put("package", "Sign=WXPay");//默认sign=WXPay
        signParam.put("partnerid", partnerid);//微信商户账号
        signParam.put("prepayid", prepayid);//预付订单id
        signParam.put("timestamp", seconds);//时间戳
        map.put("nonce_str", noncestr);
        String signAgain = WXSignUtils.createSign("UTF-8", signParam);//再次生成签名
        map.put("sign", signAgain);
        map.put("seconds", seconds);
        return map;

    }

    /**
     * 获取服务器IP地址
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getServerIp() {
        String localip = null;
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localip;
    }

    public static void main(String[] args) {
        System.out.println(getServerIp());
    }
}
