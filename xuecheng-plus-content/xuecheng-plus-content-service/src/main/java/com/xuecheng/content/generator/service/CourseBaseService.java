package com.xuecheng.content.generator.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.QueryCourseParamsDTO;
import com.xuecheng.content.po.CourseBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hxm
* @description 针对表【course_base(课程基本信息)】的数据库操作Service
* @createDate 2024-04-18 16:16:52
*/
public interface CourseBaseService extends IService<CourseBase> {
    /**
     * 课程分页查询
     * @param pageParams
     * @param queryCourseParamsDTO
     * @return
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParamsDTO);
}
