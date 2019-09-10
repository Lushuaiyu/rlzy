package com.nado.rlzy.utils;

import java.security.MessageDigest;

/**
 * @ClassName 计算CheckSum
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-09 11:36
 * @Version 1.0
 */
public class CheckSumBuilder {
    /**
     * 计算并获取CheckSum
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 11:39 2019-09-09
     * @Param [appSecret, nonce, curTime]
     * @return java.lang.String
     */
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 11:39 2019-09-09
     * @Param [requestBody]
     * @return java.lang.String
     */
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}