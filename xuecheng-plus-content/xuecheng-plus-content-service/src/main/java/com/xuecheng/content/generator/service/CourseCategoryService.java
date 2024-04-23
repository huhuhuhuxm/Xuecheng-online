package com.xuecheng.content.generator.service;

import com.xuecheng.content.dto.CourseCategoryTreeDTO;
import com.xuecheng.content.po.CourseCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hxm
* @description 针对表【course_category(课程分类)】的数据库操作Service
* @createDate 2024-04-18 16:16:52
*/
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 课程分类树形表查询
     * @param id
     * @return
     */
    List<CourseCategoryTreeDTO> queryTreeNodes(String id);
}
