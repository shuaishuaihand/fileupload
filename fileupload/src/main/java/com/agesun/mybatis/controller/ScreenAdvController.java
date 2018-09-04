package com.agesun.mybatis.controller;
import com.agesun.mybatis.bean.AdvList;
import com.agesun.mybatis.service.ScreenAdvService;
import com.agesun.mybatis.util.Json;
import com.agesun.mybatis.util.PageData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* 大屏广告
* */

/**
 * @author:nishuai
 * @description:ScreenAdvController
 * @create:2018/8/22
 */
@Controller
@RequestMapping("/screen")
public class ScreenAdvController {
    private Logger LOGGER = Logger.getLogger(ScreenAdvController.class);

    @Autowired
    private ScreenAdvService screenAdvService;
    //视频文件存放目录
    private static String videoRealPathDir;

    //视频帧图片存放路径
    private static String videoFrameDirs;

    //图片文件存放路径
    private static String pictrueFrameDirs;


    //视频文件存放目录
    @Value("#{config.videoRealPathDir}")
    public  void setVideoRealPath(String videoRealPathDir){
        ScreenAdvController.videoRealPathDir=videoRealPathDir;
    }

    //图片文件存放路径
    @Value("#{config.pictrueframedirs}")
    public  void setPictrueframedirs(String pictrueFrameDirs){
        ScreenAdvController.pictrueFrameDirs=pictrueFrameDirs;
    }


    //视频文件存放目录
    @Value("#{config.videoframedirs}")
    public  void setVideoFrameDirs(String videoFrameDirs){
        ScreenAdvController.videoFrameDirs=videoFrameDirs;
    }
    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView verifylist()
    {
        ModelAndView mv=new ModelAndView("advfall");
        return mv;
    }


    /**
     * 文件上传接口（批量上传）
     * @param multipartFile
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/upload", method={RequestMethod.POST})
    @ResponseBody
    public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile[] multipartFile,
                               HttpServletRequest request,ModelMap map) {

        ModelAndView mv=new ModelAndView("advfall");
        ModelMap resultmap=screenAdvService.upload(multipartFile,request,map);
        mv.addObject("result", resultmap.get("result"));

        return mv;
    }


    /**
     * 查询所有广告信息
     *
     * @return
     */
    @RequestMapping("/adverlist/{total}/{page}")
    @ResponseBody
    public Object advwaterfalllist(@PathVariable("total") int total,@PathVariable("page") Integer page, HttpServletRequest request)
    {
        AdvList advlist=new AdvList();
        PageData pageData=new PageData(request);
        pageData.put("page",(page - 1) * total);
        pageData.put("total",total);
        List<PageData> adverlist=screenAdvService.getadverList(pageData);
        advlist.setData(adverlist);
        advlist.setTotal(5);
        return advlist;
    }


    /**
     查询所有广告信息个数
     */
    @RequestMapping("/getadverlistmaxpage")
    @ResponseBody
    public Json getadverlistmaxpage()
    {
        Json json=new Json();
        try
        {
            Integer AllAdverNum= screenAdvService.getAllAdverNum();
            Integer maxPage=(AllAdverNum/12)+1;
            json.setResult(0);
            json.setData(maxPage);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            json.setResult(1);
            json.setMsg("最大页数查询失败！");
        }
        return json;

    }



    /**
     * 修改文件名称
     *
     * @return
     */
    @RequestMapping("/updatefilename/{id}/{filename}")
    @ResponseBody
    public Json updateFileNameById(@PathVariable("id") Integer id, @PathVariable("filename") String filename)
    {
        Json json=new Json();
        try
        {
            json= screenAdvService.updateFileNameById(id,filename);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            json.setResult(1);
            json.setMsg(e.getMessage());
        }
        return json;
    }



    /**
     * 根据id删除广告信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Json deleteAdv(Integer id){
        Json json=new Json();
        try
        {
            json= screenAdvService.deleteadver(json,id,videoFrameDirs,pictrueFrameDirs,videoRealPathDir);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            json.setResult(1);
            json.setMsg(e.getMessage());
        }
        return json;

    }


    /**
     * 批量删除设备信息
     */
    @RequestMapping(value = "/batchdeleteAdv/{checkbox}")
    @ResponseBody
    public Json batchdeleteAdv(@PathVariable("checkbox") String ids){
        Json json=new Json();
        try {
            json=screenAdvService.batchdeleteAdv(ids,videoRealPathDir,videoFrameDirs,pictrueFrameDirs);

        }catch (Exception e){
            json.setResult(1);
            json.setMsg("数据删除失败");
            LOGGER.error(e.toString(),e);
        }
        return json;
    }








}
