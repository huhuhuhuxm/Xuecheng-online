package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.constant.DictionaryConstant;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.AddCourseDTO;
import com.xuecheng.content.dto.CourseBaseInfoDTO;
import com.xuecheng.content.dto.EditCourseDTO;
import com.xuecheng.content.dto.QueryCourseParamsDTO;
import com.xuecheng.content.generator.mapper.CourseCategoryMapper;
import com.xuecheng.content.generator.mapper.CourseMarketMapper;
import com.xuecheng.content.po.CourseBase;
import com.xuecheng.content.generator.service.CourseBaseService;
import com.xuecheng.content.generator.mapper.CourseBaseMapper;
import com.xuecheng.content.po.CourseCategory;
import com.xuecheng.content.po.CourseMarket;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    CourseMarketMapper courseMarketMapper;

    CourseCategoryMapper courseCategoryMapper;

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


    /**
     * 创建课程
     * @param companyId
     * @param addCourseDTO
     * @return
     */
    @Transactional
    @Override
    public CourseBaseInfoDTO createCourseBase(Long companyId, AddCourseDTO addCourseDTO) {
        //TODO 参数合法性校验

        //向课程基本表course_base表写入数据
        CourseBase courseBase = new CourseBase();
        //参数拷贝
        courseBase.setCompanyId(companyId);
        BeanUtils.copyProperties(addCourseDTO, courseBase);
        //审核状态默认为未提交
        courseBase.setAuditStatus(DictionaryConstant.CourseAuditStatus.NOT_SUBMITTED.getCode());
        //发布状态为未发布
        courseBase.setStatus(DictionaryConstant.CoursePublishStatus.UNPUBLISHED.getCode());
//        courseBase.setCreateDate(LocalDateTime.now());
        //开始插入数据
        int courseBaseInsert = courseBaseMapper.insert(courseBase);
        if (courseBaseInsert <= 0) {
            throw new RuntimeException("添加课程失败!!!");
        }

        //向课程营销信息表course_market插入数据
        CourseMarket courseMarket = new CourseMarket();
        //参数拷贝
        courseMarket.setId(courseBase.getId());
        BeanUtils.copyProperties(addCourseDTO, courseMarket);
        //保存营销信息
        Integer courseMarketInsert = this.saveOrUpdateCourseMarket(courseMarket);
        if (courseMarketInsert <= 0) {
            throw new RuntimeException("添加课程失败!!!");
        }
        //返回查询课程信息
        CourseBaseInfoDTO courseBaseInfo = this.getCourseBaseInfo(courseBase.getId());

        return courseBaseInfo;
    }

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    @Override
    public CourseBaseInfoDTO selectCourseById(Long id) {
        log.info("根据id={}查询课程基本信息", id);
        CourseBaseInfoDTO courseBaseInfo = this.getCourseBaseInfo(id);
        return courseBaseInfo;
    }

    /**
     * 修改课程
     * @param editCourseDTO
     * @return
     */
    @Transactional
    @Override
    public CourseBaseInfoDTO updateCourseBase(Long companyId, EditCourseDTO editCourseDTO) {
        //根据课程id去查机构id
        Long courseId = editCourseDTO.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            XueChengPlusException.cast("该课程不存在！！！");
        }
        //参数合法性校验
        //根据具体的业务逻辑校验
        //本机构只能修改本机构的课程
        System.out.println(companyId.equals(courseBase.getCompanyId()));
        if (!companyId.equals(courseBase.getCompanyId())) {
            XueChengPlusException.cast("修改失败！！!, 只能修改本机构的课程。");
        }
        //参数拷贝
        BeanUtils.copyProperties(editCourseDTO, courseBase);
        //更新数据库
        int i = courseBaseMapper.updateById(courseBase);
        if (i <= 0) {
            XueChengPlusException.cast("修改课程失败！！！");
        }

        //参数拷贝
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(editCourseDTO, courseMarket);
        //更新营销信息
        Integer updateCourseMarket = this.saveOrUpdateCourseMarket(courseMarket);
        if (updateCourseMarket <= 0 ) {
            XueChengPlusException.cast("修改营销信息失败！！！");
        }
        //查询数据库
        CourseBaseInfoDTO courseBaseInfo = this.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }

    /**
     * 查询课程信息
     * @param courseId
     * @return
     */
    private CourseBaseInfoDTO getCourseBaseInfo(long courseId) {
        // TODO 这里必须要从数据库里查后续修改的话可能会复用这个方法，所以需要考虑复用性
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            return null;
        }
        //课程营销表查询
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        //组装到CourseBaseInfoDTO中
        CourseBaseInfoDTO courseBaseInfoDTO = new CourseBaseInfoDTO();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDTO);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDTO);
        //查询分类信息，
        String mtName = courseCategoryMapper.selectById(courseBase.getMt()).getName();
        String stName = courseCategoryMapper.selectById(courseBase.getSt()).getName();
        courseBaseInfoDTO.setMtName(mtName);
        courseBaseInfoDTO.setStName(stName);

        return courseBaseInfoDTO;
    }

    /**
     * 营销信息保存或更新
     * @param courseMarket
     * @return
     */
    private Integer saveOrUpdateCourseMarket(CourseMarket courseMarket) {
        //TODO 参数合法性校验

        //如果课程收费，价格没有填写学需要抛出异常
        if (DictionaryConstant.CourseFees.PAID.getCode().equals(courseMarket.getCharge())) {
            if (courseMarket.getPrice() == null || courseMarket.getPrice().floatValue() <= 0) {
                XueChengPlusException.cast("课程价格不能为空必须且大于0");
            }
        }

        //从数据库查询营销信息，存在则更新，不存在责添加
        Long id = courseMarket.getId(); // 主键
        CourseMarket courseMarketFlag = courseMarketMapper.selectById(id);
        if (courseMarketFlag == null ) {
            //插入数据库
            int insert = courseMarketMapper.insert(courseMarket);
            return insert;
        } else {
            //参数拷贝
//            BeanUtils.copyProperties(courseMarket, courseMarketFlag);
            // TODO 这边有异议 courseMarket传过来没有id不就是传过来没有id的么还是什么 文档写的也烂不如直接给需求
//            courseMarketFlag.setId(courseMarket.getId());
            // 更新
            int i = courseMarketMapper.updateById(courseMarket);
            return i;
        }
    }
}




