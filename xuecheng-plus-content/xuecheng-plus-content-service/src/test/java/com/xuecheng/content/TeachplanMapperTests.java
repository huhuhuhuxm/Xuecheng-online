package com.xuecheng.content;

import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.generator.mapper.TeachplanMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TeachplanMapperTests {

    @Resource
    TeachplanMapper teachplanMapper;

    @Test
    void teachplanMapperTest() {
        List<TeachplanDTO> teachplanDTOS = teachplanMapper.queryAllByCourseId(121L);
        System.out.println(teachplanDTOS);
    }

}
