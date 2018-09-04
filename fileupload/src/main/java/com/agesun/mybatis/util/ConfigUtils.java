package com.agesun.mybatis.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author:lh
 * @description:配置信息
 * @create:2018/04/20 11:09
 */
public class ConfigUtils {
    private static ConfigUtils INSTANCE=new ConfigUtils();
    //算法检验人脸
    private transient String server_imgcheck;
    //唯一性校验
    private transient String server_algorithmurl;
    //特征数组和人员维护
    private transient String server_feature;
    private transient String server_compare;
    private transient String server_url;
    private transient String faceport;
    private transient String model_name;
    private transient String product_name;
    private transient String threshold;

    //登录首页地址
    private transient String LOGIN_URL;
    //APP图片上传本地存储路径
    private transient String PATH;
    private transient String ACCESSORY_PATH;
    //消息推送服务IP地址
    private transient String PUSH_HOSTNAME;
    //视频文件存放目录
    private transient String videoRealPathDir;
    //查看视频文件的路径
    private transient String findvideoPath;
    //图片文件存放路径
    private transient String  pictrueframedirs;
    //查看图片文件路径
    private transient String  findpictrueframepath;
    //视频帧图片存放路径
    private transient String videoframedirs;
    //查看视频帧图片路径
    private transient String  findvideoframepath;
    //ffmpeg路勁
    private transient String ffmpegpath;

    private ConfigUtils(){
        getConfigProp();
    }

    public static ConfigUtils getInstance(){
        return INSTANCE;
    }

    public void getConfigProp() {
        Properties properties=new Properties();
        InputStream inputStream=ConfigUtils.class.getResourceAsStream("/config.properties");
        try
        {
            properties.load(inputStream);
            this.server_imgcheck=properties.getProperty("server_imgcheck","").trim();
            this.server_algorithmurl=properties.getProperty("server_algorithmurl","").trim();
            this.server_feature=properties.getProperty("server_feature","").trim();
            this.server_compare=properties.getProperty("server_compare","").trim();
            this.server_url=properties.getProperty("server_url","").trim();
            this.faceport=properties.getProperty("faceport","").trim();
            this.model_name=properties.getProperty("facepass_model_name","").trim();
            this.product_name=properties.getProperty("facepass_product_name","").trim();
            this.threshold=properties.getProperty("threshold","").trim();
            this.LOGIN_URL=properties.getProperty("LOGIN_URL","").trim();
            this.PATH=properties.getProperty("PATH","").trim();
            this.ACCESSORY_PATH=properties.getProperty("ACCESSORY_URL","").trim();
            this.PUSH_HOSTNAME=properties.getProperty("PUSH_HOSTNAME","").trim();
            this.videoRealPathDir=properties.getProperty("videoRealPathDir","").trim();
            this.pictrueframedirs=properties.getProperty("pictrueframedirs","").trim();
            this.videoframedirs=properties.getProperty("videoframedirs","").trim();
            this.findvideoPath=properties.getProperty("findvideoPath","").trim();
            this.findpictrueframepath=properties.getProperty("findpictrueframepath","").trim();
            this.findvideoframepath=properties.getProperty("findvideoframepath","").trim();
            this.ffmpegpath=properties.getProperty("ffmpegpath","").trim();
        }catch(Exception e){

        }
    }

    public String getServer_imgcheck() {
        return server_imgcheck;
    }

    public String getServer_algorithmurl() {
        return server_algorithmurl;
    }

    public String getServer_feature() {
        return server_feature;
    }

    public String getServer_compare() {
        return server_compare;
    }

    public String getServer_url() {
        return server_url;
    }

    public String getFaceport() {
        return faceport;
    }

    public String getModel_name() {
        return model_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getLOGIN_URL() {
        return LOGIN_URL;
    }
    public String getPATH() {
        return PATH;
    }

    public String getACCESSORY_PATH() {
        return ACCESSORY_PATH;
    }
    public String getPUSH_HOSTNAME() {
        return PUSH_HOSTNAME;
    }

    public String getVideoRealPathDir() {
        return videoRealPathDir;
    }

    public String getPictrueframedirs() {
        return pictrueframedirs;
    }

    public String getVideoframedirs() {
        return videoframedirs;
    }

    public String getFfmpegpath() {
        return ffmpegpath;
    }


    public String getFindvideoPath() {
        return findvideoPath;
    }

    public String getFindpictrueframepath() {
        return findpictrueframepath;
    }

    public String getFindvideoframepath() {
        return findvideoframepath;
    }
}
