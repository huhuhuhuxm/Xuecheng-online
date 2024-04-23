package com.xuecheng.content.generator.mapper;

import com.xuecheng.content.dto.CourseCategoryTreeDTO;
import com.xuecheng.content.po.CourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author hxm
* @description 针对表【course_category(课程分类)】的数据库操作Mapper
* @createDate 2024-04-18 16:16:52
* @Entity com.xuecheng.generator.entity.CourseCategory
*/
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    /**
     * 使用递归查询分类
     */
    List<CourseCategoryTreeDTO> selectTreeNodes(String id);

}




