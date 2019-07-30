package com.nado.rlzy.utils.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;


public class AlipaySubmit {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";


//
//AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
//AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//request.setBizContent("{" +
//"\"out_trade_no\":\"20150320010101001\"," +
//"\"trade_no\":\"2014112611001004680073956707\"," +
//"\"refund_amount\":200.12," +
//"\"refund_reason\":\"正常退款\"," +
//"\"out_request_no\":\"HZ01RF001\"," +
//"\"operator_id\":\"OP001\"," +
//"\"store_id\":\"NJ_S_001\"," +
//"\"terminal_id\":\"NJ_T_001\"" +
//"  }");
//AlipayTradeRefundResponse response = alipayClient.execute(request);
//if(response.isSuccess()){
//System.out.println("调用成功");
//} else {
//System.out.println("调用失败");
//}


    //生成发给客户端支付需要的数据包
    public static String getAppPag(String orderId, String body, String subject, BigDecimal money) {
        //实例化客户端                                                                                                                                                          问下安卓这里的参数
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APPID,
                AlipayConfig.pivateKey, "json", AlipayConfig.input_charset, AlipayConfig.publicKey, AlipayConfig.sign_type);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK�Ѿ���װ���˹�������������ֻ��Ҫ����ҵ����������·���Ϊsdk��model��η�ʽ(model��biz_contentͬʱ���ڵ������ȡbiz_content)��
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        //�뱣֤OutTradeNoֵÿ�α�֤Ψһ
        model.setOutTradeNo(orderId);
        model.setTimeoutExpress(AlipayConfig.it_b_pay);
        model.setTotalAmount(String.valueOf(money.doubleValue()));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        String orderStr = "";
        try {
            //�������ͨ�Ľӿڵ��ò�ͬ��ʹ�õ���sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();//����orderString ����ֱ�Ӹ��ͻ�������������������
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return orderStr;
    }


    //对账单查询
    public static String queryOrder() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APPID, AlipayConfig.pivateKey, "json", AlipayConfig.input_charset, AlipayConfig.publicKey, "RSA2");
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\"2018-03\"" +
                "  }");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return response.getBody();
        } else {
            System.out.println("调用失败");
            return "";
        }
    }


    /**
     * 生成签名结果(MD5签名)
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if (AlipayConfig.sign_type.equals("MD5")) {
            mysign = MD5.sign(prestr, AlipayConfig.pivateKey, AlipayConfig.input_charset);
        }
        return mysign;
    }

    /**
     * 生成要请求给支付宝的参数数组（MD5）
     *
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * @param orderId
     * @param title
     * @param subject
     * @param money
     * @return
     */
    public static String getzfbApi(String orderId, String title, String subject, BigDecimal money) {
        String signOrderUrl = null;
        String[] parameters = {
                "service=\"mobile.securitypay.pay\"",//固定值
                "partner=\"" + AlipayConfig.partner + "\"",//商户号
                "_input_charset=\"" + AlipayConfig.input_charset + "\"",
                "notify_url=\"" + AlipayConfig.notify_url + "\"",//通知地址
                "out_trade_no=\"" + orderId + "\"",//商户内部订单号
                "subject=\"" + title + "\"",//说明
                "payment_type=\"1\"",//固定值
                "seller_id=\"" + AlipayConfig.seller_email + "\"",//账户
                "total_fee=\"" + money.toString() + "\"",//支付金额（元）
                "body=\"" + title + "\"",//标题
                "it_b_pay=\"" + AlipayConfig.it_b_pay + "\""
        };
        signOrderUrl = signAllString(parameters);
        return signOrderUrl;

    }

    private static String signAllString(String[] array) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < array.length; i++) {
            if (i == (array.length - 1)) {
                sb.append(array[i]);
            } else {
                sb.append(array[i] + "&");
            }
        }
        System.out.println(sb.toString());
        String sign = "";
        try {
            sign = URLEncoder.encode(RSA.sign(sb.toString(), AlipayConfig.pivateKey, "utf-8"), "utf-8");//private_key私钥
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&sign=\"" + sign + "\"&");
        sb.append("sign_type=\"RSA\"");
        return sb.toString();
    }

}
