package com.nado.rlzy.utils.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public final static String partner = "2088821848424861";
    // 收款支付宝账号，一般情况下收款账号就是签约账号（即登录账号，可不配）
    public final static String seller_email = "yunzhixue@aliyun.com";
    // 商户的私钥
    public static final String pivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCq0YZ/25mOSbV6bnnuqsWs06tbM2E2htKgtWXeTGyFYCdEOBpPRSS+xDUFOQhtFQ1HQTKKhqAOyKfjJJJoK/0+nHQW2HtWaBWqk3I4DfgU0+DMlKcR/2fj5KDT0pqONW8gETiaRW+WZJ4Nc/pVBN18yoqY5+TFBdWDtk+6FKR7LXuVG/f2gvWfeTTVKy4MjvN4rNMfGVuEwomJXhH5Pr36+HMG54bR1J3F64OC7o7k2LBgWcb2mEjD0BKdSxoQzQSOBr09F90/NldFX3/p4R+/z88jwOmcajb5tVGz1IK92p42/vrlmYtaQBmupa9VuVfcqRJAol3U91xDspnbCuBtAgMBAAECggEBAI9hZAY6bAAVOrFTviFpUpeo7HKXBAhizrV6zdD0UTr65LrMZLkZDqjZxK9nmf0F6Klgvm2sZvlB0DpG3mV2VxGQ/ZiXiZ+TqmwNa5hFkXDaEB1owXvH6IpPw/dJFdY8X9SI3/wOQIX6Wvhr2zo15Tv0PrXuIAaIyRnPk8hbv1xsBZACBodenb4tWrUONJemkHipmjRNKyIUKazVz3YxZ8OXJYC4ex1E0+s1iinSd7qUc4BdTkbSm/3rvb0pd3xVd3sU4FlbANhwpceA3WjyxBa36FQi/81/cRJgVAyutP86COYvC5U7PK1VNAOwhvoESBV+XQYvAFzyTZBcM5hIwnkCgYEA6yNlnfBxCpM8S4N42F1yLBin2kVWN7Tot3bLjBiYZ6hZP/cdjKuA2YxoFqzPrUV/4JhJWdMxe7pdJN4BWNUDkxHSQ6fvfRpPO3qzQpAkinsCDwUVDbG7tG9tU/iN8oLL7C9kP+ie0TfhFBX6rHJE+QM0DR7mSzvQTJZk4BZoScsCgYEAuflCBBdgXXNePLFhuZcfMrrvCjDV/XDmaiaasYd7GGCbTcsLaxF4N9KHj4AIl5rqb1DZ61FujNnCUohn9E3d4homXhjJNNun6Qmpi6nybk0T+EyPV2oOlAeUiIAKMnV+ZvlMmIyui+R33cjSbM3dZ26RoOqdul1GZZmLi6z0l6cCgYBtf4tXtFt/2DbQ14WUBFghrs2juHxA7s8ceIarU9zNELmXLwWkt00GgfsK7Ci+AMpfFau6172IZ63ueC/aZUAH4dmvxemhsXnKMgZ+bc+GaWf5BJ3cRbMqgGVCIFJB56T89MjTdglXVpVLHD0ixwfViidYINaPnGgQ8kxBik3AZwKBgQCzmDOZcd28wWHbKM2gBYG6Yu3fIIQ2qSXKkxdGo/hK9VhlStTvA8Ld3bDN8q3ZgmAtOZ4I8u3YlZ5kR5hkRCInbGtq6XK9WfaQRKV325njzQmg+oZeu2vyplNZcpTmX7K+zLechhc1v1/GTV5wdmn+rbdy9790LwmBqkMpks/t9wKBgGIbbuLXHglrggub6MwM2ywXdOBBOA9G/nDarLPkyOh3/OOD+Hk8g2ldLP9mSAOR7PHISYzdkvYMEIKqZq7v4ErEXu7PnqEmeb4OsvQ7Ww1LHy1bLE1QTEvuAd5euDe+OqtlibNRYyNpOOPUL9g+nSrF2HwEY810FKmSU19y63ob";
    //编码格式
    public final static String input_charset = "utf-8";
    // 签名方式 不需修改
    public final static String sign_type = "RSA2";
    //域名
    public final static String realmname = "http://yunzhixue.nadoapp.com";
    //public final static String realmname = "http://test.diewin.cn";
    //public final static String realmname = "http://localhost:8080";
    //回调接口
    public final static String notify_url = realmname + "/yunzhixue/order/async.do";
    //支付时间限时
    public final static String it_b_pay = "30m";
    public final static String APPID = "2017112700204921";
    // 支付宝公钥
    public final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj74OWKN5YMmRqWnuxpByffZLk/sFg6e1qQCoQqXkUD37n3an2/+j0E95NIYgplM58bSYpOXXQatgmQQ9ngK0vrKZWDR2xcxGIHqmwh+rgzXfffI0oWLcYtLl3PsGSERcO/aILZLqDP3MROrGyD6f+y+IbkAjpEoulXnOh9DwA4dF6XdNAVlhRiqLdtafH8xuShkMaO7pEbNnKvgc8PfHO95DUJp1hBQCp11nWd4YH4aHJpYJex1U9JGOT/5Uo9wHfYnRClqOLAD2hKwv1XxGoIHJHSdUtMBmz464AIsBGEiY/YN2aKWRjHQcnzpLK9dh4V+RzvLQyS5n5IHy9LJ2NQIDAQAB";

}
