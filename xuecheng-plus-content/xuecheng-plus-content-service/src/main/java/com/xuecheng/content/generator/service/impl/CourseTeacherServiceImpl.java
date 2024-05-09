package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.po.CourseTeacher;
import com.xuecheng.content.generator.service.CourseTeacherService;
import com.xuecheng.content.generator.mapper.CourseTeacherMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hxm
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher>
    implements CourseTeacherService{

    CourseTeacherMapper courseTeacherMapper;

    /**
     * 根据课程id查询教师信息
     * @param courseId
     * @return
     */
    @Override
    public List<CourseTeacher> selectTeacherById(Long courseId) {
        //查询与courseId匹配的老师信息
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId, courseId);
        List<CourseTeacher> courseTeacher = courseTeacherMapper.selectList(lambdaQueryWrapper);
        return courseTeacher;
    }

    /**
     * 保存或更新教师信息
     * @param courseTeacher
     * @return
     */
    @Override
    public CourseTeacher saveTeacher(CourseTeacher courseTeacher) {
        if (courseTeacher.getId() == null) {
            int flag = courseTeacherMapper.insert(courseTeacher);
            if (flag <= 0) {
                XueChengPlusException.cast("保存失败");
            }
            // 查询插入结果返回 mybaitplus插入时候自动返回主键id
            Long id = courseTeacher.getId();
            return courseTeacherMapper.selectById(id);
        } else {
            int flag = courseTeacherMapper.updateById(courseTeacher);
            if (flag <= 0) {
                XueChengPlusException.cast("修改失败");
            }
            return courseTeacher;
        }
    }

    /**
     * 删除教师信息
     * @param courseId
     * @param teacherId
     */
    @Override
    public void deleteTeacher(Long courseId, Long teacherId) {
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId, courseId)
                .eq(CourseTeacher::getId, teacherId);
        int delete = courseTeacherMapper.delete(lambdaQueryWrapper);
        if (delete <= 0) {
            XueChengPlusException.cast("删除失败");
        }
    }
}




