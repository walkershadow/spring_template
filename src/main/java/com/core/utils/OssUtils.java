package com.core.utils;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.model.config.OssConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Random;

/**
 * @author qam
 * @time 2015年5月19日 上午9:18:49
 */
public class OssUtils {
    private static final Logger logger = Logger.getLogger(OssUtils.class);

    /**
     * 上传 数据到OSS
     *
     * @param img
     * @return
     */
    public static String uploadImg(MultipartFile img) {
        try {
            String imageName = OssConfig.FILE_NAME + "_" + DateUtil.format(new Date(), "yyyy/MM/dd/HHmmss") + new Random().nextInt(10000) + ".jpg";
            return OssUtils.upload2Yun(OssConfig.PATH, imageName, img.getBytes(), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 图片上传到阿里OSS对象存储中
     *
     * @param imagePath 图片上传路径，例：“loanUser/2016/09/23/”，注意：不以斜杠开头、以斜杠结尾
     * @param dimension 尺寸，大于0进行对应压缩
     * @return 上传后图片路径
     */
    private static String upload2Yun(String imagePath, String imageName, byte[] imgByte,
                                     int dimension) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(OssConfig.ENDPOINT, OssConfig.KEY_ID, OssConfig.KEY_SECRET);
        // 得到上传文件的后缀名
        String filePath = imagePath + imageName + ".jpg";

        InputStream inputStream = null;
        if (dimension > 0) {
            ByteArrayInputStream in = new ByteArrayInputStream(imgByte);
            BufferedImage srcBufferImage = ImageIO.read(in);

            BufferedImage scaledImage = ImageUtils.compressImage(srcBufferImage, dimension);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", os);
            inputStream = new ByteArrayInputStream(os.toByteArray());
            if (srcBufferImage != null) {
                srcBufferImage.flush();
            }
        } else {
            inputStream = new ByteArrayInputStream(imgByte);
        }
        // 上传文件流
        PutObjectResult putObjectResult = ossClient.putObject(OssConfig.BUCKET_NAME, filePath, inputStream);
        putObjectResult.getCallbackResponseBody();

        // 关闭client
        ossClient.shutdown();

        return OssConfig.DOMAIN + filePath;
    }


    /**
     * 上传 数据到OSS
     *
     * @param result
     * @return
     */
    public static String uploadFile(String result) {
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(result.getBytes("Utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("data to inputstream is unsupportedEncoding !");
        }

        String fileName = OssConfig.FILE_NAME + "_" + DateUtil.format(new Date(), "yyyy/MM/dd/HHmmss") + new Random().nextInt(10000) + ".jpg";
        String path = StringUtils.EMPTY;
        try {
            path = OssUtils.uploadFileYun(fileName + ".js", inputStream);
        } catch (IOException e) {
            logger.error("upload oss have some exception !");
        }
        return path;
    }

    /**
     * 文件上传到阿里OSS对象存储中
     *
     * @param filePath    上传路径，例：“loanUser/2016/09/23/”+fileName，注意：不以斜杠开头、以斜杠结尾
     * @param inputStream 文件流
     * @return 上传后的文件路径
     * @throws IOException
     * @author WangYaL
     * @dateTime 2016年12月19日 下午5:00:17
     */
    public static String uploadFileYun(String filePath, InputStream inputStream) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(OssConfig.ENDPOINT, OssConfig.KEY_ID, OssConfig.KEY_SECRET);
        // 上传文件流
        PutObjectResult putObjectResult = ossClient.putObject(OssConfig.BUCKET_NAME, filePath, inputStream);
        putObjectResult.getCallbackResponseBody();
        // 关闭client
        ossClient.shutdown();
        return filePath;
    }

    public static String getFileFromYun(String filePath) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(OssConfig.ENDPOINT, OssConfig.KEY_ID, OssConfig.KEY_SECRET);
        // 下载文件流
        OSSObject ossObject = ossClient.getObject(OssConfig.BUCKET_NAME, filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return sb.toString();
    }


}
