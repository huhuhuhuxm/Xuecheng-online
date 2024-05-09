package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.AddCourseDTO;
import com.xuecheng.content.dto.CourseBaseInfoDTO;
import com.xuecheng.content.dto.EditCourseDTO;
import com.xuecheng.content.dto.QueryCourseParamsDTO;
import com.xuecheng.content.generator.service.CourseBaseService;
import com.xuecheng.content.po.CourseBase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 课程信息管理
 */
@Slf4j
@RestController
@RequestMapping("/content")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseBaseInfoController {

    CourseBaseService courseBaseService;

    /**
     * 课程查询
     * @param pageParams
     * @param queryCourseParamsDTO
     * @return
     */
    @PostMapping("/course/list")
    public PageResult<CourseBase> pageList(PageParams pageParams,
                                           @RequestBody(required = false) QueryCourseParamsDTO queryCourseParamsDTO) {
        log.info("开始查询");
        PageResult<CourseBase> courseBasePageResult = courseBaseService.queryCourseBaseList(pageParams, queryCourseParamsDTO);
        return courseBasePageResult;
    }

    /**
     * 创建课程
     * @param addCourseDTO
     * @return
     */
    @PostMapping("/course")
    public CourseBaseInfoDTO createCourseBase(@RequestBody @Validated(value = {ValidationGroups.Insert.class}) AddCourseDTO addCourseDTO) {
        log.info("开始新增课程");
        // TODO 后续补上用户所属机构的id
        //获取用户所属机构的id
        Long companyId = 1232141425L;

        CourseBaseInfoDTO courseBaseInfoDTO  = courseBaseService.createCourseBase(companyId, addCourseDTO);
        return courseBaseInfoDTO;
    }


    /**
     * 根据id查询课程基本信息
     * @param id
     * @return
     */
    @GetMapping("/course/{id}")
    public CourseBaseInfoDTO selectCourseById(@PathVariable Long id) {
        log.info("根据id={}查询课程详细信息", id);
        CourseBaseInfoDTO  courseBaseInfoDTO = courseBaseService.selectCourseById(id);
        return courseBaseInfoDTO;
    }


    /**
     * 修改课程
     * @param editCourseDTO
     * @return
     */
    @PutMapping("/course")
    public CourseBaseInfoDTO updateCourseBase(@RequestBody @Validated(value = {ValidationGroups.Update.class}) EditCourseDTO editCourseDTO) {
        log.info("修改课程信息：{}", editCourseDTO);
        // TODO 后续补上用户所属机构的id
        //获取用户所属机构的id
        Long companyId = 1232141425L;
        CourseBaseInfoDTO courseBaseInfoDTO = courseBaseService.updateCourseBase(companyId, editCourseDTO);
        return courseBaseInfoDTO;
    }

    /**
     * 删除课程
     * @param id
     */
    @DeleteMapping("/course/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        log.info("删除id={}的课程", id);
        // TODO 后续补上用户所属机构的id
        //获取用户所属机构的id
        Long companyId = 1232141425L;
        courseBaseService.deleteCourseById(id, companyId);
    }

}
