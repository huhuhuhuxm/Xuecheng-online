package com.xuecheng.content.generator.service;

import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.po.Teachplan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hxm
* @description 针对表【teachplan(课程计划)】的数据库操作Service
* @createDate 2024-04-18 16:16:52
*/
public interface TeachplanService extends IService<Teachplan> {

    /**
     * 根据课程id查询课程计划
     * @param courseId
     * @return
     */
    List<TeachplanDTO> queryTeachPlansBycourseId(Long courseId);
}
