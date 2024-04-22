package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.QueryCourseParamsDto;
import com.xuecheng.content.generator.mapper.CourseBaseMapper;
import com.xuecheng.content.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Test
    public void testCourseBaseMapper() {
        //详细进行分页查询的单元测试
        //查询条件
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        courseParamsDto.setCourseName("java");//课程查询条件
//        courseParamsDto.setAuditStatus("202004");
        //拼装查询条件
        LambdaQueryWrapper<CourseBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNoneEmpty(courseParamsDto.getCourseName()),CourseBase::getName,courseParamsDto.getCourseName());
        //根据课程的审核状态去查询
        lambdaQueryWrapper.eq(StringUtils.isNoneEmpty(courseParamsDto.getAuditStatus()), CourseBase::getAuditStatus , courseParamsDto.getAuditStatus());
        //根据发布状态去
        lambdaQueryWrapper.eq(StringUtils.isNoneEmpty(courseParamsDto.getPublishStatus()), CourseBase::getStatus, courseParamsDto.getPublishStatus());

        //分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(2L);

        //创建page分页参数对象，参数当前页码，每页记录数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());
        //开始分页查询
//        List<CourseBase> courseBases = courseBaseMapper.selectList(lambdaQueryWrapper);
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, lambdaQueryWrapper);
        //数据列表
        List<CourseBase> records = courseBasePage.getRecords();
        //总记录数
        long total = courseBasePage.getTotal();
        //当前页码
        long current = courseBasePage.getCurrent();
        //每页显示条数
        long size = courseBasePage.getSize();

        PageResult<CourseBase> courseBasePageResult = new PageResult<>(records, total, current, size);
        System.out.println(courseBasePageResult);
//        System.out.println(courseBases);
    }
}
