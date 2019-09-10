package com.nado.rlzy.utils;

import com.nado.rlzy.platform.constants.OSSClientConstants;

import java.io.IOException;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-10 14:04
 * @Version 1.0
 */
public class OssUtilOne {
    //阿里上传图片
    public static String picUpload(String base64Img,String userId)  {
        String path = "";
        String resultPath="";
//        String timeStr=DateUtil.getDateOnString();
        Long timeStr=System.currentTimeMillis();
        if(!base64Img.contains(",")) { //单张图片
             try {
                path = OSSClientUtilOne.uploadByteObjectOSS(base64Img, OSSClientConstants.FOLDER,timeStr+"-"+userId+"-0"+".png");

            } catch (IOException e) {
                e.printStackTrace();
            }
            resultPath=path;
        }else{ //多张
            String[] a=base64Img.split(",");
            for(int i=0;i<a.length;i++){
                try {
                    path = OSSClientUtilOne.uploadByteObjectOSS(a[i],OSSClientConstants.FOLDER,timeStr+"-"+userId+"-"+i+".png");
                } catch (IOException e) {
                    e.printStackTrace();
                 }
                if(i==a.length-1){ //最后一张
                    resultPath=resultPath+path;
                }else{
                    resultPath=resultPath+path+",";
                }
            }
        }
        System.out.println("path="+path);
        return resultPath;
    }
}
