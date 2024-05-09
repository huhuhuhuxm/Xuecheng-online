package com.xuecheng.content.api;

import com.xuecheng.content.generator.service.CourseTeacherService;
import com.xuecheng.content.po.CourseTeacher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师信息相关接口
 */
@Slf4j
@RestController
@RequestMapping("/content")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseTeacherController {

    CourseTeacherService courseTeacherService;

    /**
     * 根据课程id查询教师信息
     * @param courseId
     * @return
     */
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> selectTeacherById(@PathVariable Long courseId) {
        log.info("查询courseId={}的老师", courseId);
        List<CourseTeacher> courseTeacher = courseTeacherService.selectTeacherById(courseId);
        return courseTeacher;
    }

    /**
     * 保存或者更新教师信息
     * @param courseTeacher
     * @return
     */
    @PostMapping("/courseTeacher")
    public CourseTeacher saveTeacher(@Validated @RequestBody CourseTeacher courseTeacher) {
        log.info("添加教师{}", courseTeacher);
        CourseTeacher result = courseTeacherService.saveTeacher(courseTeacher);
        return result;
    }

    /**
     * 删除教师信息
     * @param courseId
     * @param teacherId
     */
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void deleteTeacher(@PathVariable Long courseId, @PathVariable Long teacherId) {
        log.info("删除courseId={},teacherId={}的教师", courseId ,teacherId);
        courseTeacherService.deleteTeacher(courseId, teacherId);
    }
}
