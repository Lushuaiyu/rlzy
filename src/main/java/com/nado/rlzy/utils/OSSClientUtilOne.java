package com.nado.rlzy.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.nado.rlzy.platform.constants.OSSClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-10 14:02
 * @Version 1.0
 */
public class OSSClientUtilOne {
    private static SimpleDateFormat sdf = null;
    public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);


    public static OSSClient getOSSClient() {
        return new OSSClient(OSSClientConstants.ENDPOINT, OSSClientConstants.ACCESS_KEY_ID,
                OSSClientConstants.ACCESS_KEY_SECRET);
    }

    public static String createBucketName(OSSClient ossClient, String bucketName) {
        String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            Bucket bucket = ossClient.createBucket(bucketName);

            return bucket.getName();
        }
        return bucketNames;
    }

    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        String keySuffixWithSlash = folder;
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));

            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder + key);
    }

    public static String uploadObject2OSS(InputStream is, String bucket, String fileName) {
        String resultStr = "";
        try {
            OSSClient ossClient = getOSSClient();

            String folder = bucket + "/" + getDateString(OSSClientConstants.FORMAT) + "/";

            int fileSize = is.available();

            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentLength(is.available());

            metadata.setCacheControl("no-cache");

            metadata.setHeader("Pragma", "no-cache");

            metadata.setContentEncoding("utf-8");

            metadata.setContentType(getContentType(fileName));

            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");

            PutObjectResult putResult = ossClient.putObject(OSSClientConstants.BACKET_NAME, folder + fileName, is,
                    metadata);

            resultStr = OSSClientConstants.DOMAINURL + folder + fileName;

            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    public static String uploadFileOSS(String file) {
        String resultStr = "";
        try {
            OSSClient ossClient = getOSSClient();

            String folder = getDateString(OSSClientConstants.FORMAT) + "/";

            file = get32UUID() + file.substring(file.lastIndexOf("."));

            Integer fileSize = Integer.valueOf(file.length());

            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setCacheControl("no-cache");

            metadata.setHeader("Pragma", "no-cache");

            metadata.setContentEncoding("utf-8");

            metadata.setContentType(getContentType(file));

            metadata.setContentDisposition("filename/filesize=" + file + "/" + fileSize + "Byte.");

            PutObjectResult putResult = ossClient.putObject(OSSClientConstants.BACKET_NAME, folder + file,
                    new ByteArrayInputStream(file.getBytes("UTF-8")), metadata);

            resultStr = folder + file;

            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    public static String uploadByteObjectOSS(String base64Str, String bucket, String fileName) throws IOException {
        OSSClient ossClient = getOSSClient();
        String folder = bucket + "/" + getDateString(OSSClientConstants.FORMAT) + "/";
        base64Str = base64Str.replaceAll("data:image/jpeg;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imgByte = null;
        try {
            imgByte = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < imgByte.length; i++) {
                if (imgByte[i] < 0) {
                    int tmp86_84 = i;
                    byte[] tmp86_82 = imgByte;
                    tmp86_82[tmp86_84] = ((byte) (tmp86_82[tmp86_84] + 256));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long fileSize = Long.valueOf(imgByte.length);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize.longValue());
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(getContentType(fileName));
        metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
        InputStream in = new ByteArrayInputStream(imgByte);
        PutObjectResult putResult = ossClient.putObject(OSSClientConstants.BACKET_NAME, folder + fileName, in,
                metadata);
        ossClient.shutdown();
        in.close();
        String filepath = OSSClientConstants.DOMAINURL + folder + fileName;
        return filepath;
    }

    public static String uploadFileOSS(ByteArrayOutputStream stream, String bucket, String fileName) {
        String resultStr = "";
        try {
            OSSClient ossClient = getOSSClient();

            Integer fileSize = Integer.valueOf(fileName.length());

            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setCacheControl("no-cache");

            metadata.setHeader("Pragma", "no-cache");

            metadata.setContentEncoding("utf-8");

            metadata.setContentType(getContentType(fileName));

            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            ByteArrayInputStream swapStream = new ByteArrayInputStream(stream.toByteArray());

            PutObjectResult putResult = ossClient.putObject(OSSClientConstants.BACKET_NAME, bucket + "/" + fileName,
                    swapStream, metadata);

            resultStr = OSSClientConstants.DOMAINURL + bucket + "/" + fileName;
            swapStream.close();

            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    public static OSSObject downLoadFile(OSSClient ossClient, String fileName) {
        OSSObject ossObject = ossClient.getObject(OSSClientConstants.BACKET_NAME, fileName);
        return ossObject;
    }

    public static byte[] String2Bytes(String base64Str) {
        base64Str = base64Str.replace("data:image/jpeg;base64,", "");

        byte[] imgByte = null;
        try {
            imgByte = Base64.getDecoder().decode(base64Str);
            for (int i = 0; i < imgByte.length; i++) {
                if (imgByte[i] < 0) {
                    int tmp33_32 = i;
                    byte[] tmp33_31 = imgByte;
                    tmp33_31[tmp33_32] = ((byte) (tmp33_31[tmp33_32] + 256));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgByte;
    }

    public static String getDateString(String format) {
        sdf = new SimpleDateFormat(format);
        return sdf.format(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static byte[] image2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));

        byte[] bytes = new byte[fin.available()];

        fin.read(bytes);
        fin.close();
        return bytes;
    }

    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte['?'];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".png".equalsIgnoreCase(fileExtension)) {
            return "image/png";
        }
        if ((".jpeg".equalsIgnoreCase(fileExtension)) || (".jpg".equalsIgnoreCase(fileExtension))
                || (".png".equalsIgnoreCase(fileExtension))) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if ((".ppt".equalsIgnoreCase(fileExtension)) || ("pptx".equalsIgnoreCase(fileExtension))) {
            return "application/vnd.ms-powerpoint";
        }
        if ((".doc".equalsIgnoreCase(fileExtension)) || ("docx".equalsIgnoreCase(fileExtension))) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)) {
            return "video/mp4";
        }
        return "image/jpeg";
    }

	/*public static String getUrl(OSSClient ossClient, String bucketName, String fileName) {
		Date expiration = new Date(new Date().getTime() + 315360000000L);

		URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
		if (url != null) {
			return url.toString();
		}
		return "��������������";
	}*/
}
