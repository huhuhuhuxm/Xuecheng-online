package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.po.Teachplan;
import com.xuecheng.content.generator.service.TeachplanService;
import com.xuecheng.content.generator.mapper.TeachplanMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hxm
* @description 针对表【teachplan(课程计划)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
    implements TeachplanService{

    TeachplanMapper teachplanMapper;

    /**
     * 根据课程id查询课程计划
     * @param courseId
     * @return
     */
    @Override
    public List<TeachplanDTO> queryTeachPlansBycourseId(Long courseId) {
        List<TeachplanDTO> teachplanDTOS = teachplanMapper.queryAllByCourseId(courseId);
        return teachplanDTOS;
    }
}




