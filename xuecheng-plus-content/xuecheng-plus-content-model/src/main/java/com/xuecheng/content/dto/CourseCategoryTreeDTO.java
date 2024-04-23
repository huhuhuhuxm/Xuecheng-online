package com.xuecheng.content.dto;

import com.xuecheng.content.po.CourseCategory;
import lombok.Data;

import java.util.List;

/**
 * 课程查询树形模型类
 */
@Data
public class CourseCategoryTreeDTO extends CourseCategory {
    //子节点
    private List<CourseCategoryTreeDTO> childrenTreeNodes;
}
