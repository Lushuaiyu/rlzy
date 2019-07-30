package com.nado.rlzy.utils;

/**
 * @ClassName 创建随机数量的字符串
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/10 20:40
 * @Version 1.0
 */
public class RandomCodesUtils {

    /**
     * 创建指定数量的随机字符串
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:40 2019/7/10
     * @param numberFlag 是否是纯数字
     * @param length  长度
     * @return java.lang.String
     **/
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;

    }

    public static void main(String[] args) {
        System.out.println("验证码:"+createRandom(true,6));
    }
}