package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.po.CourseTeacher;
import com.xuecheng.content.generator.service.CourseTeacherService;
import com.xuecheng.content.generator.mapper.CourseTeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author hxm
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher>
    implements CourseTeacherService{

}




