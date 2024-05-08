package com.xuecheng.content.api;

import com.xuecheng.content.dto.SaveTeachplanDTO;
import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.generator.service.TeachplanMediaService;
import com.xuecheng.content.generator.service.TeachplanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程计划编辑相关接口
 */
@Slf4j
@RestController
@RequestMapping("/content")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeachplanController {

    TeachplanService teachplanService;

    /**
     * 根据课程id查询课程计划
     * @param courseId
     * @return
     */
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDTO> queryTeachPlanBycourseId(@PathVariable Long courseId) {
        log.info("查询id={}的课程计划", courseId);
        List<TeachplanDTO> teachplanDTOS = teachplanService.queryTeachPlansBycourseId(courseId);
        return teachplanDTOS;
    }

    /**
     * 新增或更新课程计划
     * @param saveTeachplanDTO
     */
    @PostMapping("/teachplan")
    public void saveOrUpdateTeachplan(@RequestBody SaveTeachplanDTO saveTeachplanDTO) {
        log.info("修改或更新课程计划：{}", saveTeachplanDTO);
        teachplanService.saveOrUpdateTeachplan(saveTeachplanDTO);
    }

    /**
     * 根据id删除课程计划
     * @param id
     */
    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachplanById(@PathVariable Long id) {
        log.info("删除id={}的课程计划", id);
        teachplanService.deleteTeachplanById(id);
    }

    /**
     * 根据id课程计划移动
     * @param id
     */
    @PostMapping("/teachplan/{moveType}/{id}")
    public void teachlanMove(@PathVariable String moveType,@PathVariable Long id) {
        log.info("id={}的课程计划{}", id, moveType);
        teachplanService.moveUpOrDown(id, moveType);
    }
}
