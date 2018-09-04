package com.agesun.mybatis.util.constant;

import com.agesun.mybatis.util.ConfigUtils;

/**
 * @author:lh
 * @description:配置信息静态变量
 * @create:2018/04/20 11:16
 */
public class Config {
    public static String server_imgcheck = ConfigUtils.getInstance().getServer_imgcheck();

    public static String server_algorithmurl=ConfigUtils.getInstance().getServer_algorithmurl();

    public static String server_feature=ConfigUtils.getInstance().getServer_feature();

    public static String server_compare=ConfigUtils.getInstance().getServer_compare();

    public static String server_url=ConfigUtils.getInstance().getServer_url();

    public static String faceport=ConfigUtils.getInstance().getFaceport();

    public static String model_name=ConfigUtils.getInstance().getModel_name();

    public static String product_name=ConfigUtils.getInstance().getProduct_name();

    public static float threshold= Float.parseFloat(ConfigUtils.getInstance().getThreshold());
    /**
     * 登录地址
     */
    public static String LOGIN_URL=ConfigUtils.getInstance().getLOGIN_URL();

    /**
     *图片上传路径
     */
    public static String PATH=ConfigUtils.getInstance().getPATH();
    public static String ACCESSORY_PATH=ConfigUtils.getInstance().getACCESSORY_PATH();
    //消息推送服务IP地址
    public static String PUSH_HOSTNAME=ConfigUtils.getInstance().getPUSH_HOSTNAME();

    //视频文件存放目录
    public static String videoRealPathDir=ConfigUtils.getInstance().getVideoRealPathDir();
    //图片文件存放路径
    public static String pictrueframedirs=ConfigUtils.getInstance().getPictrueframedirs();
    //视频帧图片存放路径
    public static String videoframedirs=ConfigUtils.getInstance().getVideoframedirs();
    //查看视频文件的路径
    public static String findvideoPath=ConfigUtils.getInstance().getFindvideoPath();
    //查看图片文件路径
    public static String  findpictrueframepat=ConfigUtils.getInstance().getFindpictrueframepath();
    //查看视频帧图片路径
    public static String  findvideoframepath=ConfigUtils.getInstance().getFindvideoframepath();
    //ffmpeg路径
    public static String ffmpegpath=ConfigUtils.getInstance().getFfmpegpath();
}
