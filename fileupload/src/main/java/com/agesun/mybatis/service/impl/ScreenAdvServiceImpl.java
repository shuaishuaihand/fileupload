package com.agesun.mybatis.service.impl;
import com.agesun.mybatis.bean.FileEntity;
import com.agesun.mybatis.bean.JsonDateValueProcessor;
import com.agesun.mybatis.dao.ScreenAdvMapper;
import com.agesun.mybatis.service.ScreenAdvService;
import com.agesun.mybatis.util.FileUploadTool;
import com.agesun.mybatis.util.HelpUtil;
import com.agesun.mybatis.util.Json;
import com.agesun.mybatis.util.PageData;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:nishuai
 * @description:ScreenAdvServiceImpl
 * @create:2018/8/15
 */
@Service
public class ScreenAdvServiceImpl implements ScreenAdvService {

    @Resource
    private ScreenAdvMapper screenAdvMapper;

    /**
     将上传的文件信息，保存到数据库中
     */
    @Override
    public int SaveFileEntiy(FileEntity fileEntity){
      return screenAdvMapper.SaveFileEntiy(fileEntity);

    }

    /**
     查询所有广告信息
     */
    @Override
    public List<PageData> getadverList(PageData pageData){
        String adv_type=String.valueOf(pageData.get("filetype"));
        if(adv_type.equals("2")||adv_type.equals("undefined") || "null".equals(adv_type)){
            adv_type="";
        }
        pageData.put("adv_type",adv_type);
        List<PageData> adverlist=screenAdvMapper.getadverList(pageData);
        JsonDateValueProcessor jsonDateValueProcessor=new JsonDateValueProcessor();
        for (int i=0;i<adverlist.size();i++){
            //处理文件类型。为前端加上图片/视频样式
            Integer adv_type2 =Integer.parseInt(String.valueOf(adverlist.get(i).get("adv_type")));
            if(adv_type2==0){
                adverlist.get(i).put("adv_type","glyphicon glyphicon-facetime-video");
            }
            if(adv_type2==1){
                adverlist.get(i).put("adv_type","glyphicon glyphicon-picture");
            }

            //处理时长格式 1054 00:00:00
            Integer adv_time = HelpUtil.strToInt(String.valueOf(adverlist.get(i).get("adv_time")),0);
            String newadv_time=this.secToTime(adv_time);
            adverlist.get(i).put("adv_time",newadv_time);

            //处理文件格式.flv=>flv
            String  file_type=  String.valueOf(adverlist.get(i).get("file_type"));
            file_type= file_type.substring(1);
            adverlist.get(i).put("file_type",file_type);


            //处理时间格式
            Object createTime=  adverlist.get(i).get("create_time");
            Object newcreateTime= jsonDateValueProcessor.processObjectValue(null,createTime,null);
            adverlist.get(i).put("create_time",newcreateTime);

            //处理名称过长
            String file_name=String.valueOf(adverlist.get(i).get("file_name"));
            if(file_name.length()>15){
              String omitfilename=file_name.substring(0,15)+"...";
              adverlist.get(i).put("file_name",omitfilename);
            }

        }


      return  adverlist;
    }


    /**
     查询所有广告信息个数
     */
    @Override
    public Integer getAllAdverNum(){
        return screenAdvMapper.getAllAdverNum();
    }

    /**
     文件上传接口
     */
    @Override
    public ModelMap upload(MultipartFile[] multipartFile, HttpServletRequest request, ModelMap map){
        String message = "";
        FileUploadTool fileUploadTool = new FileUploadTool();
        Map<String,Object> filemap=new HashMap<String,Object>();
        //判断file数组不能为空并且长度大于0
        if (multipartFile!= null && multipartFile.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < multipartFile.length; i++) {
                MultipartFile file = multipartFile[i];

                //校验文件名称
                if (!this.checkUploadFileType(file)) {
                    message = "上传失败";
                    map.put("result", message);
                } else{

                    try {
                        //上传文件，获得一部分属性
                        filemap = fileUploadTool.createFile(file, request);
                        if (filemap != null) {
                            //给其他为null的属性赋值
                            FileEntity newentity = this.SetAttribute(filemap);

                            //将上传的文件信息，保存到数据库中
                            this.SaveFileEntiy(newentity);
                            message = "上传成功";
                            map.put("entity", newentity);
                            map.put("result", message);
                        } else {
                            message = "上传失败";
                            map.put("result", message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

              }
            }

        }

      return map;

    }

    public  Boolean checkUploadFileType(MultipartFile file){
        FileUploadTool fileUploadTool = new FileUploadTool();

        String fileName = file.getOriginalFilename().toString();
       /*String fileName.substring(fileName.lastIndexOf("."));*/
        Boolean flag = true;
        // 文件类型判断
        if ( fileUploadTool.checkFileType(fileName)) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }



    /**
     * 修改文件名称
     */

    public Json updateFileNameById(Integer id, String filename){
         Json json=new Json();
        //名称重复性校验
        int count= screenAdvMapper.checkRepetAdvName(filename);
        if(count>0){
            json.setResult(1);
            json.setMsg("文件已经存在，操作失败!");
        }else {

            int result = screenAdvMapper.updateFileNameById(id, filename);
            if (result > 0) {
                json.setResult(0);
                json.setMsg("修改成功!");
            } else {
                json.setResult(1);
                json.setMsg("修改失败！");
            }
        }
        return  json;
    }

    @Override
    public int getAdvTotalCount(PageData pageData) {
        return screenAdvMapper.getAdvTotalCount(pageData);
    }


    /**
     * 根据id删除广告信息
     */
    @Override
    public Json  deleteadver(Json json, Integer id, String videoFrameDirs, String pictrueFrameDirs, String videoRealPathDir){
        //根据id判断，文件是否使用过程中
        int num= screenAdvMapper.isNotFileInUse(id);
        //查询文件信息
        FileEntity fileEntity= screenAdvMapper.selectAdvById(id);
        //文件类型
        Integer adv_type=fileEntity.getAdvtype();
        //文件adv_url
        String adv_url=fileEntity.getAdvurl();
        String[] advurlist = adv_url.split("/");
        String lastadvurlname=advurlist[advurlist.length-1];

        //文件file_url
        String file_url=fileEntity.getFileurl();
        String[] fileurlist;
        String lastfileurlname="";
        if(!file_url.equals("")) {
            fileurlist = file_url.split("/");
            lastfileurlname = fileurlist[fileurlist.length - 1];
        }
        if(num>0){
            json.setResult(1);
            json.setMsg("文件在使用中，无法删除!");

        }else {
            int result = screenAdvMapper.deleteAdver(id);
            //将文件删除
            //1 视频文件时，删除视频和帧图片
              if(adv_type==0){
                  String videoframepath=videoFrameDirs+lastadvurlname;
                  String videopath=videoRealPathDir+lastfileurlname;
                  HelpUtil.deleteImage(videoframepath);
                  HelpUtil.deleteImage(videopath);
              }

             //2 图片文件时，删除图片文件
             if(adv_type==1) {
                  String pictruepath=pictrueFrameDirs+lastadvurlname;
                  HelpUtil.deleteImage(pictruepath);
             }
            if (result > 0) {
                json.setResult(0);
                json.setMsg("删除成功!");
            } else {
                json.setResult(1);
                json.setMsg("删除失败！");
            }
        }
        return  json;
    }

    /**
     * 批量删除广告信息
     */
    @Override
    public Json batchdeleteAdv(String ids, String videoRealPathDir, String videoFrameDirs, String pictrueFrameDirs)throws Exception{
        Json json = new Json();
        String[] idlist = ids.split(",");
        if (idlist.length > 0)
        {
            for (int i = 0; i < idlist.length; i++)
            {
                Integer id=Integer.parseInt(String.valueOf(idlist[i]));
                //先判断文件是否使用过程中
                int num= screenAdvMapper.isNotFileInUse(id);
                if(num>0){
                    json.setResult(1);
                    json.setMsg("有文件正在使用中，无法删除!");
                    break;
                }
                else{
                    //查询文件信息
                    FileEntity fileEntity= screenAdvMapper.selectAdvById(id);
                    //文件类型
                    Integer adv_type=fileEntity.getAdvtype();
                    //文件adv_url
                    String adv_url=fileEntity.getAdvurl();
                    String[] advurlist = adv_url.split("/");
                    String lastadvurlname=advurlist[advurlist.length-1];

                    //文件file_url
                    String file_url=fileEntity.getFileurl();
                    String[] fileurlist;
                    String lastfileurlname="";
                    if(!file_url.equals("")) {
                        fileurlist = file_url.split("/");
                        lastfileurlname = fileurlist[fileurlist.length - 1];
                    }

                    screenAdvMapper.deleteAdver(id);
                    json.setResult(0);
                    json.setMsg("数据删除成功！");
                    //将文件删除
                    //1 视频文件时，删除视频和帧图片
                    if(adv_type==0){
                        String videoframepath=videoFrameDirs+lastadvurlname;
                        String videopath=videoRealPathDir+lastfileurlname;
                        HelpUtil.deleteImage(videoframepath);
                        HelpUtil.deleteImage(videopath);
                    }

                    //2 图片文件时，删除图片文件
                    if(adv_type==1) {
                        String pictruepath=pictrueFrameDirs+lastadvurlname;
                        HelpUtil.deleteImage(pictruepath);
                    }
                }
            }

        }
        return json;
    }

    /**
     *
      为FileEntity的null的属性赋值
     */
    public FileEntity SetAttribute(Map<String, Object> filemap){
       FileEntity fileEntity=new FileEntity();

        //广告类型（视频，图片判断）
        Integer adv_type=Integer.parseInt(String.valueOf(filemap.get("adv_type")));
        fileEntity.setAdvtype(adv_type);

        // 文件格式
        String file_type=String.valueOf(filemap.get("file_type"));
        fileEntity.setFiletype(file_type);

        // 源文件经过处理后的name
        String adv_origin_name=String.valueOf(filemap.get("adv_origin_name"));

        //文件名称
        //当第二次，上传相同的文件时，文件名称变成（1）、（2）
        String name=String.valueOf(filemap.get("file_name"));
        //从数据查询文件名称相同的，记录个数，加1，如spring（1），spring(2),...
        String filenamelike=name+"(%)";
        String newname="";
        int i=  screenAdvMapper.selectSimilarFileNameNum(filenamelike,name);
        // 依据原始文件名生成新文件名
        if(i>0){
          newname=name+"("+i+")";
            fileEntity.setFilename(newname);
        }else {
            fileEntity.setFilename(name);
        }

        // 文件绝对路径
        String file_url =String.valueOf(filemap.get("file_url"));
        if(adv_type==0) {
            fileEntity.setFileurl(file_url);
        }
        if(adv_type==1){
            fileEntity.setFileurl(file_url);
        }

        //视频帧图片存放路径赋值给实体类
        String adv_url=String.valueOf(filemap.get("adv_url"));
        if(adv_type==0) {
            fileEntity.setAdvurl(adv_url);
        }
        if(adv_type==1){
            fileEntity.setAdvurl(adv_url);
        }

        //视频时长
        String adv_time=String.valueOf(filemap.get("adv_time"));
        fileEntity.setAdvtime(HelpUtil.strToInt(adv_time,0));

        //视频分辨率
        String adv_resolving_power=String.valueOf(filemap.get("adv_resolving_power"));
        fileEntity.setResolution(adv_resolving_power);


        // size
        String adv_size=String.valueOf(filemap.get("adv_size"));
        fileEntity.setAdvsize(adv_size);

        // size
        String create_time=String.valueOf(filemap.get("create_time"));
        fileEntity.setCreatetime(create_time);


        return fileEntity;

    }


    /**
     *
     处理时长格式 1054===> 00:00:00
     */
    public  String secToTime(int time) {

        String timeStr = null;

        if(time==0){
            timeStr="";
        }

        if(time>0) {
            int hour = time / 3600;
            int minute = time / 60 % 60;
            int second = time % 60;

            timeStr = hour + ":" + minute + ":" + second;

        }

       return timeStr;
    }
}
