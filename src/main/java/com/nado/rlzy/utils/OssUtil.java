package com.nado.rlzy.utils;


import com.nado.rlzy.platform.exception.ImgException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName 图片上传工具类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-10 10:53
 * @Version 1.0
 */
public class OssUtil {

    public static String ossImageList(String[] image) {
        List<String> collect = Stream.of(image).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();

        collect.stream()
                .map(dto -> {
                    MultipartFile multipartFile = Base64Util.base64ToMultipart(dto);
                    String head = OssUtil.updateHead(multipartFile);
                    strings.add(head);
                    return dto;
                }).collect(Collectors.toList());
        String collect1 = strings.stream().collect(Collectors.joining(","));
        return collect1;
    }

    public static String ossImageListt(String image) {
        String[] split = image.split(",");

        List<String> collect = Stream.of(split).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();

        collect.stream()
                .map(dto -> {
                    MultipartFile multipartFile = Base64Util.base64ToMultipart(dto);
                    String head = OssUtil.updateHead(multipartFile);
                    strings.add(head);
                    return dto;
                }).collect(Collectors.toList());
        String collect1 = strings.stream().collect(Collectors.joining(","));
        return collect1;
    }


    public static String updateHead(MultipartFile file) throws ImgException {

        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    public static String ossImage(String image) {
        MultipartFile multipartFile = Base64Util.base64ToMultipart(image);
        String head = OssUtil.updateHead(multipartFile);
        return head;


    }


}
