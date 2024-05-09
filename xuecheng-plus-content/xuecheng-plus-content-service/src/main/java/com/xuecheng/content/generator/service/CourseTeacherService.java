package com.xuecheng.content.generator.service;

import com.xuecheng.content.po.CourseTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hxm
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service
* @createDate 2024-04-18 16:16:52
*/
public interface CourseTeacherService extends IService<CourseTeacher> {

    /**
     * 根据课程id查询教师信息
     * @param courseId
     * @return
     */
    List<CourseTeacher> selectTeacherById(Long courseId);

    /**
     * 保存或更新教师信息
     * @param courseTeacher
     * @return
     */
    CourseTeacher saveTeacher(CourseTeacher courseTeacher);

    /**
     * 删除教师信息
     * @param courseId
     * @param teacherId
     */
    void deleteTeacher(Long courseId, Long teacherId);
}
