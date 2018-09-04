package com.agesun.mybatis.bean;

public class FileEntity {
    private Integer id;
    //广告类型：0视频，1图片
    private Integer advtype;
    //广告占用空间
    private String advsize;
    //广告时长—视频
    private Integer advtime;
    //上传日期
   /* private Timestamp createtime;*/
     private String createtime;
    //广告分辨率
    private String resolution;
    //广告地址(视频帧地址，图片地址)
    private String advurl;
    //文件名称
    private String filename;
    //文件格式
    private String filetype;
    //文件地址（视频地址）
    private String fileurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdvtype() {
        return advtype;
    }

    public void setAdvtype(Integer advtype) {
        this.advtype = advtype;
    }

    public String getAdvsize() {
        return advsize;
    }

    public void setAdvsize(String advsize) {
        this.advsize = advsize;
    }

    public Integer getAdvtime() {
        return advtime;
    }

    public void setAdvtime(Integer advtime) {
        this.advtime = advtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAdvurl() {
        return advurl;
    }

    public void setAdvurl(String advurl) {
        this.advurl = advurl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
