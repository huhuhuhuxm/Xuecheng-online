package com.xuecheng.content.api;

import com.xuecheng.content.dto.CourseCategoryTreeDTO;
import com.xuecheng.content.generator.service.CourseCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程分类
 */
@Slf4j
@RestController
@RequestMapping("/content")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseCategoryController {

    CourseCategoryService courseCategoryService;

    /**
     * 课程分类树形表查询
     * @return
     */
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDTO> queryTreeNodes() {
        List<CourseCategoryTreeDTO> courseCategoryTreeDTOList = courseCategoryService.queryTreeNodes("1");
        return courseCategoryTreeDTOList;
    }

}
