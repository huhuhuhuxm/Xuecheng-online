package com.xuecheng.content.generator.service;

import com.xuecheng.content.dto.SaveTeachplanDTO;
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

    /**
     * 新增或更新课程计划
     * @param saveTeachplanDTO
     */
    void saveOrUpdateTeachplan(SaveTeachplanDTO saveTeachplanDTO);

    /**
     * 根据id删除课程计划
     * @param id
     */
    void deleteTeachplanById(Long id);

    /**
     * 课程计划移动
     * @param id
     * @param moveType
     */
    void moveUpOrDown(Long id, String moveType);
}
