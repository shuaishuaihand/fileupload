package com.agesun.mybatis.dao;
import com.agesun.mybatis.bean.FileEntity;


import com.agesun.mybatis.util.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreenAdvMapper {

    /**
     将上传的文件信息，保存到数据库中
     */
    int SaveFileEntiy(FileEntity fileEntity);

    /**
     查询文件名相同的个数
     */
    int selectSimilarFileNameNum(@Param("filenamelike") String filenamelike, @Param("filename") String filename);


    /**
     查询所有广告信息
     */
    List<PageData> getadverList(PageData pageData);

    /**
     查询所有广告信息个数
     */
     Integer getAllAdverNum();

    /**
     * 根据id删除广告信息
     */
    int deleteAdver(Integer id);

    /**
     * 判断文件是否在使用过程中
     */
    int isNotFileInUse(Integer id);


    /**
     * 修改广告名称信息
     */
    int updateFileNameById(@Param("id") Integer id, @Param("filename") String filename);

    /**
     * 检查广告名称信息是否重复
     */
    int checkRepetAdvName(String filename);

    /**
     * 根据id,查询单个文件信息
     */
    FileEntity selectAdvById(Integer id);

    int getAdvTotalCount(PageData pageData);

}
