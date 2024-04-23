package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.QueryCourseParamsDTO;
import com.xuecheng.content.po.CourseBase;
import com.xuecheng.content.generator.service.CourseBaseService;
import com.xuecheng.content.generator.mapper.CourseBaseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author hxm
* @description 针对表【course_base(课程基本信息)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
    implements CourseBaseService{

    CourseBaseMapper courseBaseMapper;

    /**
     * 课程分页查询
     * @param pageParams
     * @param queryCourseParamsDTO
     * @return
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParamsDTO) {
        log.info("分页查询: " + pageParams + queryCourseParamsDTO);
        LambdaQueryWrapper<CourseBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据审核状态查询
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDTO.getAuditStatus())
                , CourseBase::getAuditStatus, queryCourseParamsDTO.getAuditStatus());
        //根据课程名称查询
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDTO.getCourseName())
                , CourseBase::getName, queryCourseParamsDTO.getCourseName());
        //根据发布状态查询
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDTO.getPublishStatus())
                , CourseBase::getStatus, queryCourseParamsDTO.getPublishStatus());
        //分页查询
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, lambdaQueryWrapper);

        //返回数据
        return new PageResult<>(courseBasePage.getRecords(),
                courseBasePage.getTotal(),
                courseBasePage.getCurrent(),
                courseBasePage.getSize());
    }
}




