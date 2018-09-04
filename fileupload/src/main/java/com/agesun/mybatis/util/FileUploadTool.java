package com.agesun.mybatis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class FileUploadTool {

    // 文件最大500M
    private static long upload_maxsize = 800 * 1024 * 1024;
    // 文件允许格式
    private static String[] allowFiles = { ".rmvb", ".avi", ".mp4", ".flv",
            ".jpg", ".bmp", ".png"};
    //视频文件格式
    private static String[] videofile = { ".rmvb", ".avi", ".mp4", ".flv"};
    //图片文件格式
    private static String[] picturefile = { ".jpg", ".bmp", ".png"};
    // 允许转码的视频格式（ffmpeg）
    private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp",
            ".mov", ".asf", ".asx", ".vob" };
    // 允许的视频转码格式(mencoder)
    private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };

    //视频文件存放目录
    private static String videoRealPathDir;

    //图片文件存放路径
    private static String pictrueFrameDirs;

    //视频帧图片存放路径
    private static String videoFrameDirs;

    //#ffmpeg路径
    private static String ffmpegPath;

    //查看视频文件存放目录
    private static String findVideoPath;

    //查看图片文件路径
    private static String findPictrueFramePath;

    //查看视频帧图片路径
    private static String findVideoFramePath;

    //获取配置文件中的数据

    //视频文件存放目录
    @Value("#{config.videoRealPathDir}")
    public  void setVideoRealPath(String videoRealPathDir){
        FileUploadTool.videoRealPathDir=videoRealPathDir;
    }


    @Value("#{config.pictrueframedirs}")
    public  void setPictrueframedirs(String pictrueFrameDirs){
        FileUploadTool.pictrueFrameDirs=pictrueFrameDirs;
    }

    //视频文件存放目录
    @Value("#{config.videoframedirs}")
    public  void setVideoFrameDirs(String videoFrameDirs){
        FileUploadTool.videoFrameDirs=videoFrameDirs;
    }

    //视频文件存放目录
    @Value("#{config.ffmpegpath}")
    public  void setffmpegPath(String ffmpegPath){
        FileUploadTool.ffmpegPath=ffmpegPath;
    }


    @Value("#{config.findvideoPath}")
    public  void setFindVideoPath(String findVideoPath){
        FileUploadTool.findVideoPath=findVideoPath;
    }

    //查看图片文件路径
    @Value("#{config.findpictrueframepath}")
    public  void setFindPictrueFramePath(String findPictrueFramePath){
        FileUploadTool.findPictrueFramePath=findPictrueFramePath;
    }

    @Value("#{config.findvideoframepath}")
    public  void setFindVideoFramePath(String findVideoFramePath){
        FileUploadTool.findVideoFramePath=findVideoFramePath;
    }



    public Map<String,Object> createFile(MultipartFile multipartFile, HttpServletRequest request) {
        Map<String,Object> filemap= new HashMap<String,Object>();
        boolean bflag = false;
        String fileName = multipartFile.getOriginalFilename().toString();
        // 判断文件不为空
        if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
            bflag = true;
            // 判断文件大小
            if (multipartFile.getSize() <= upload_maxsize) {
                bflag = true;
                // 文件类型判断
                if (this.checkFileType(fileName)) {
                    bflag = true;
                } else {
                    bflag = false;
                    System.out.println("文件类型不允许");
                }
            } else {
                bflag = false;
                System.out.println("文件大小超范围");
            }
        } else {
            bflag = false;
            System.out.println("文件为空");
        }
        if (bflag) {

            // 文件扩展名（文件格式）
            String fileEndName = this.getFileExt(fileName);
            filemap.put("file_type",fileEndName);

            //视频文件存放目录videoRealPathDir
            String logoRealPathDir= videoRealPathDir;

            File video=new File(logoRealPathDir);
            if(!video.exists()){
                video.mkdirs();
            }
            //视频帧图片存放路径
            String videoframedirs="";

            //图片文件存放路径
            String  pictrueframedirs=pictrueFrameDirs+fileName;


            //文件名称
            String name = fileName.substring(0, fileName.lastIndexOf("."));
            filemap.put("file_name",name);

            //图片名称 UUID.randomUUID().toString())
            String advpictruename= UUID.randomUUID().toString().replaceAll("-","");
            filemap.put("adv_origin_name",advpictruename);



            //判断文件类型（视频，图片)，
            //0:视频的话，将视频放在视频目录，并截取视频的某一帧，放到视频帧图片目录
            //1:图片的话，直接将图片放在广告图片目录

            //广告类型（视频，图片判断）
            if(this.checkFileIsNotVideo(fileEndName)){
                filemap.put("adv_type",0);//视频

                // 视频绝对路径（修改名字之后）
                String videoabsolutedirsrename = logoRealPathDir + File.separator + advpictruename + fileEndName;

                // 视频相对路径
                String videorelativepath=findVideoPath+advpictruename+fileEndName;
                filemap.put("file_url",videorelativepath);

                // 上传到本地磁盘
                File filedirs = new File(videoabsolutedirsrename);

                // 转入文件
                try {
                    multipartFile.transferTo(filedirs);

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //判断视频帧图片路径是否存在
                File videoframe=new File(videoFrameDirs);
                if(!videoframe.exists()){
                    videoframe.mkdirs();
                }

                //视频帧图片存放路径
                videoframedirs=videoFrameDirs+advpictruename+".png";

                //截取视频的第几秒（也是第几帧）图片
                VideoThumbTaker videoThumbTaker = new VideoThumbTaker(ffmpegPath);
                try
                {
                    videoThumbTaker.getThumb(videoabsolutedirsrename, videoframedirs,    1600, 900, 0, 0, 3);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                //视频帧图片存放路径赋值给实体类
                filemap.put("adv_url",findVideoFramePath+advpictruename+".png");


                //获取视频的总时长和分辨率
                Map<String,Object> map=new HashMap<String, Object>();
                try
                {
                    GetVideoTime videotime = new GetVideoTime();
                    map=videotime.getVideoTime(videoabsolutedirsrename,ffmpegPath);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                //视频时长
                int adv_time=HelpUtil.strToInt(map.get("adv_time").toString(),0);
                filemap.put("adv_time",adv_time);

                //视频分辨率
                String adv_resolving_power=String.valueOf(map.get("adv_resolving_power"));
                filemap.put("adv_resolving_power",adv_resolving_power);

                // size存储为String
                String size = this.getSize(filedirs);
                filemap.put("adv_size",size);

            }
            if(this.checkFileIsNotPictrue(fileEndName)){
                filemap.put("adv_type",1); //图片

                filemap.put("file_url","");

                //上传图片
                Map<String, String> map = this.upload(multipartFile);
                String url=null;
                if(map.size()!=0)
                {
                    // 图片文件的相对地址路径
                    url = map.get("image");
                    filemap.put("adv_url",url);
                }


                //视频时长
                filemap.put("adv_time",0);

                filemap.put("adv_resolving_power","");
                // size存储为String

                filemap.put("adv_size",map.get("size"));
            }



            return filemap;

        } else {
            return null;
        }

    }

    /**
     *
     * 上传图片方法
     *
     */
    public static Map<String, String> upload(MultipartFile file) {
        Map<String, String> map = new HashMap<String, String>();
        // 存储图片的路径
        String uploadPath1 =pictrueFrameDirs;
        // 查看照片路径
        String uploadPath = findPictrueFramePath;

        File directoryFile1 = new File(uploadPath1);
        // 若存储照片路径不存在
        if (!directoryFile1.exists() && !directoryFile1.isDirectory()) {
            directoryFile1.mkdirs();
        }

        if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (!"".equals(myFileName.trim())) {
                        // 截取文件类型
                        // 重命名上传后的文件名
                        String fileName = UUID.randomUUID().toString().replaceAll("-", "")
                                + myFileName.substring(myFileName.lastIndexOf("."));
                        // 定义上传路径
                        String loadpath = uploadPath1 + fileName;
                        // 定义保存的图片地址
                        String savepath = uploadPath + fileName;
                        map.put("image", savepath);
                        File localFile = new File(loadpath);
                        if (!localFile.exists()) {
                            try {
                                file.transferTo(localFile);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            map.put("size", getSize(localFile));
                        }
                    }
                }

        return map;
    }

    /**
     * 判断文件是否是视频文件
     *
     * @param fileName
     * @return
     */
    private boolean checkFileIsNotVideo(String fileName) {
        Iterator<String> type = Arrays.asList(videofile).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断文件是否是图片
     *
     * @param fileName
     * @return
     */
    private boolean checkFileIsNotPictrue(String fileName) {
        Iterator<String> type = Arrays.asList(picturefile).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    public boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }



    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     * @return
     */
    private String getName(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.contains(ext)) {
                String newFileName = fileName.substring(0, fileName.lastIndexOf(ext));
                return newFileName;
            }
        }
        return "";
    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private static String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }
        return size;
    }
}
