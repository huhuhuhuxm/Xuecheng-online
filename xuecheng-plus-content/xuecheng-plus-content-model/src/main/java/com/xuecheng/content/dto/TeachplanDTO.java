package com.xuecheng.content.dto;

import com.xuecheng.content.po.Teachplan;
import com.xuecheng.content.po.TeachplanMedia;
import lombok.Data;

import java.util.List;

/**
 * 课程计划DTO
 */
@Data
public class TeachplanDTO extends Teachplan {
    //媒资
    private TeachplanMedia teachplanMedia;
    //课程计划子章节list
    private List<TeachplanDTO> teachPlanTreeNodes;
}
