package com.nado.rlzy.utils;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.config.NeteaseConfig;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 网易云信
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-09 11:05
 * @Version 1.0
 */
public class NetEaseSendUtil {

    public static String netEaseSendUtil(StringEntity requestEntity, String url) throws Exception {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建HttpPost对象
        HttpPost post = new HttpPost(url);
        // 签名
        String appKey = NeteaseConfig.appKey;
        String appSecret = NeteaseConfig.appSecret;
        //随机数
        String nonce = DateUtil.getFourRandom();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        // 计算CheckSum
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        // 设置请求的header
        post.addHeader("AppKey", appKey);
        post.addHeader("Nonce", nonce);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum", checkSum);
//        post.addHeader("Content-Type", "application/json;charset=utf-8");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求参数
        post.setEntity(requestEntity);
        // 执行请求并处理响应
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String result = null;
        if (response.getStatusLine().getStatusCode() == 200 && entity != null) {
            result = EntityUtils.toString(entity);
        }
        // 释放资源
        response.close();
        httpClient.close();
        return result;
    }

    /**
     * 注册网易云信
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 13:48 2019-09-09
     * @param id 这里可以用 userId
     * @param name 网易云通信ID昵称，最大长度64字符，用来PUSH推送
     * 时显示的昵称
     * @param icon 网易云通信ID头像URL，第三方可选填，最大长度1024
     * @return java.lang.String
     */
    public static String create(String id, String name,String icon) throws Exception {
        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", id));
        nvps.add(new BasicNameValuePair("name", name));
        nvps.add(new BasicNameValuePair("icon", icon));
        StringEntity requestEntity = new UrlEncodedFormEntity(nvps, "utf-8");
        String str = netEaseSendUtil(requestEntity, NeteaseConfig.createUrl);
        String token = null;
        if (null != str) {
            /** 把json字符串转换成json对象 **/
            JSONObject jsonResult = JSONObject.parseObject(str);
            JSONObject jsonResult2 = JSONObject.parseObject(jsonResult.getString("info"));
            token = jsonResult2.getString("token");
        }
        return token;
    }
}
