package com.agesun.mybatis.service;
import com.agesun.mybatis.bean.FileEntity;

import com.agesun.mybatis.util.Json;
import com.agesun.mybatis.util.PageData;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ScreenAdvService {

    /**
     将上传的文件信息，保存到数据库中
     */
    int SaveFileEntiy(FileEntity fileEntity);

     /**
       文件上传接口
     */
    ModelMap  upload(MultipartFile[] multipartFile, HttpServletRequest request, ModelMap map);

     /**
     查询所有广告信息
     */

    List<PageData> getadverList(PageData pageData);

    /**
     查询所有广告信息个数
     */

    Integer getAllAdverNum();

    /**
     根据id删除广告信息
     */

    Json deleteadver(Json json, Integer id, String videoFrameDirs, String pictrueFrameDirs, String videoRealPathDir);

    /**
     * 批量删除广告信息
     */
    Json batchdeleteAdv(String ids, String videoRealPathDir, String videoFrameDirs, String pictrueFrameDirs) throws Exception;

    /**
     * 修改文件名称
     */
    Json updateFileNameById(Integer id, String filename);



    int getAdvTotalCount(PageData pageData);
}
