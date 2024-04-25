package com.xuecheng.content;

import com.xuecheng.content.dto.CourseCategoryTreeDTO;
import com.xuecheng.content.generator.mapper.CourseCategoryMapper;
import com.xuecheng.content.generator.service.CourseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class CourseCategoryMapperTests {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Resource
    private CourseCategoryService courseCategoryService;
    @Test
    void testCourseCategoryMapper() {
//        List<CourseCategoryTreeDTO> courseCategoryTreeDTOS = courseCategoryMapper.selectTreeNodes("1");
//
//        System.out.println(courseCategoryTreeDTOS.get(1).getId());


        List<CourseCategoryTreeDTO> courseCategoryTreeDTOS = courseCategoryService.queryTreeNodes("1");
        System.out.println(courseCategoryTreeDTOS);

    }

    enum Color {
        RED,
        GREEN,
        BLUE
    }

    @Test
    void testEnum() {
        System.out.println(Color.RED);
    }


}


