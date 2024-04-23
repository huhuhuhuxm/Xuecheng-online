package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.QueryCourseParamsDTO;
import com.xuecheng.content.generator.service.CourseBaseService;
import com.xuecheng.content.po.CourseBase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程信息管理
 */
@Slf4j
@RestController
@RequestMapping("/content/course")
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
    @PostMapping("list")
    public PageResult<CourseBase> pageList(PageParams pageParams,
                                           @RequestBody(required = false) QueryCourseParamsDTO queryCourseParamsDTO) {
        log.info("开始查询");
        PageResult<CourseBase> courseBasePageResult = courseBaseService.queryCourseBaseList(pageParams, queryCourseParamsDTO);
        return courseBasePageResult;
    }
}
