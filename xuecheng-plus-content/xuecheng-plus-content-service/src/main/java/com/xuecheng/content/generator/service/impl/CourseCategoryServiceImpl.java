package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.dto.CourseCategoryTreeDTO;
import com.xuecheng.content.generator.mapper.CourseCategoryMapper;
import com.xuecheng.content.po.CourseCategory;
import com.xuecheng.content.generator.service.CourseCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author hxm
* @description 针对表【course_category(课程分类)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
    implements CourseCategoryService{

    CourseCategoryMapper courseCategoryMapper;

    /**
     * 课程分类树形表查询
     * @param id
     * @return
     */
    @Override
    public List<CourseCategoryTreeDTO> queryTreeNodes(String id) {
        //调用mapper递归查询出分类信息
        List<CourseCategoryTreeDTO> courseCategoryTreeDTOS = courseCategoryMapper.selectTreeNodes("1");
        //找到每个子节点，最终封装到List<CourseCategoryTreeDTO>中
        //将list转换成map，key就是节点的id，value就是CourseCateTreeDto对象，目的就是方便从map中获取节点
        Map<String, CourseCategoryTreeDTO> treeDTOMap = courseCategoryTreeDTOS.stream()
                .filter(courseCategoryTree -> !id.equals(courseCategoryTree.getId()))
                .collect(Collectors.toMap(key -> key.getId(), Function.identity(), (existingValue, newValue) -> newValue));
        //定义一个list集合
        ArrayList<CourseCategoryTreeDTO> categoryTreeList = new ArrayList<>();

        //从头遍历List<CourseCategoryTreeDTO>，遍历吧子节点放在父节点
        courseCategoryTreeDTOS.stream()
                .filter(courseCategoryTree -> !id.equals(courseCategoryTree.getId()))
                .forEach( item -> {
                    //把父节点等于id的节点存起来
                    if (item.getParentid().equals(id)) {
                        categoryTreeList.add(item);
                    }
                    //找出当前节点的父节点
                    CourseCategoryTreeDTO courseCategoryParent = treeDTOMap.get(item.getParentid());
                    //判断父节点是否为null
                    if (courseCategoryParent != null) {
                        if (courseCategoryParent.getChildrenTreeNodes() == null) {
                            //判断该父节点的子节点是否为null，如果为null则new一个新的列表
                            courseCategoryParent.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDTO>());
                        }
                        //把符合条件的节点放入父节点
                        courseCategoryParent.getChildrenTreeNodes().add(item);
                    }
                });
        return categoryTreeList;
    }
}




