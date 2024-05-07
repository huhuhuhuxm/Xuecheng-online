package com.xuecheng.content.api;

import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.generator.service.TeachplanMediaService;
import com.xuecheng.content.generator.service.TeachplanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
