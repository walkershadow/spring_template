package com.core.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片压缩工具类
 * 提供的方法中可以设定生成的缩略图片的大小尺寸等
 *
 * @author WangYaL 邮箱：wang_yalong@qq.com
 * @dateTime 2016年10月9日 上午11:32:00
 */
public class ImageUtils {

    /**
     * 将图片按照指定的图片尺寸压缩
     *
     * @param src       :源图片
     * @param newWeight :压缩后的图片宽
     * @param newHigh   :压缩后的图片长
     */
    public static BufferedImage compressImage(BufferedImage src, int newWeight, int newHigh) {
        return src == null ? null : disposeImage(src, newWeight, newHigh);
    }

    /**
     * 指定长或者宽的最大值来压缩图片
     *
     * @param src       :源图片
     * @param maxLength :长或者宽的最大值
     */
    public static BufferedImage compressImage(BufferedImage src, int maxLength) {
        if (src == null) {
            return null;
        }
        //得到源图宽
        int oldWeight = src.getWidth();
        //得到源图长
        int oldHigh = src.getHeight();
        //新图的宽
        int newWeight = 0;
        //新图的长
        int newHigh = 0;

        // 根据图片尺寸压缩比得到新图的尺寸
        if (oldWeight > oldHigh) {
            // 图片要缩放的比例
            newWeight = maxLength;
            newHigh = (int) Math.round(oldHigh * ((float) maxLength / oldWeight));
        } else {
            newWeight = (int) Math.round(oldWeight * ((float) maxLength / oldHigh));
            newHigh = maxLength;
        }
        return disposeImage(src, newWeight, newHigh);
    }

    /**
     * 处理图片
     *
     * @param src   源图片
     * @param newWeight 压缩后的图片宽
     * @param newHigh 压缩后的图片高
     */
    private synchronized static BufferedImage disposeImage(BufferedImage src,
                                                           int newWeight, int newHigh) {
        // 得到源图宽
        int oldWeight = src.getWidth();
        // 得到源图长
        int oldHigh = src.getHeight();

        BufferedImage newImg = null;
        // 判断输入图片的类型
        switch (src.getType()) {
            case 13:
                newImg = new BufferedImage(newWeight, newHigh, BufferedImage.TYPE_INT_RGB);
                break;
            default:
                newImg = new BufferedImage(newWeight, newHigh, BufferedImage.TYPE_INT_RGB);
                break;
        }
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, oldWeight, oldHigh, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(
                src.getScaledInstance(newWeight, newHigh, Image.SCALE_SMOOTH), 0, 0,
                null);
        return newImg;
    }
}
