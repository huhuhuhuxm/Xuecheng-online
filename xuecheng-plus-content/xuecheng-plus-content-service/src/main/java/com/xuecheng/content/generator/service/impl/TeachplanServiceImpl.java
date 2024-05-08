package com.xuecheng.content.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.constant.DictionaryConstant;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.dto.SaveTeachplanDTO;
import com.xuecheng.content.dto.TeachplanDTO;
import com.xuecheng.content.generator.mapper.TeachplanMediaMapper;
import com.xuecheng.content.po.Teachplan;
import com.xuecheng.content.generator.service.TeachplanService;
import com.xuecheng.content.generator.mapper.TeachplanMapper;
import com.xuecheng.content.po.TeachplanMedia;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.xuecheng.base.constant.DictionaryConstant.ORDERBY_INITIVAL_VALUE;

/**
* @author hxm
* @description 针对表【teachplan(课程计划)】的数据库操作Service实现
* @createDate 2024-04-18 16:16:52
*/
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
    implements TeachplanService{

    TeachplanMapper teachplanMapper;

    TeachplanMediaMapper teachplanMediaMapper;

    /**
     * 根据课程id查询课程计划
     * @param courseId
     * @return
     */
    @Override
    public List<TeachplanDTO> queryTeachPlansBycourseId(Long courseId) {
        List<TeachplanDTO> teachplanDTOS = teachplanMapper.queryAllByCourseId(courseId);
        return teachplanDTOS;
    }

    /**
     * 新增或更新课程计划
     * @param saveTeachplanDTO
     */
    @Override
    public void saveOrUpdateTeachplan(SaveTeachplanDTO saveTeachplanDTO) {
        //根据是否有id来判断新增还是更新操作
        Long id = saveTeachplanDTO.getId();
        if (id != null) {
            //执行更新操作
            //进行数据拷贝
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDTO, teachplan);
            teachplanMapper.updateById(teachplan);
        } else {
            //执行新增操作
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDTO, teachplan);
            // 查询节点中order最大的值然后，在最大值的基础上+1
            int maxOrderby = this.getMaxOrderby(teachplan);
            //在orderby最大值上
            teachplan.setOrderby(maxOrderby + 1);
            teachplanMapper.insert(teachplan);
        }
    }

    /**
     * 根据id删除课程计划
     * @param id
     */
    @Transactional
    @Override
    public void deleteTeachplanById(Long id) {
        LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teachplan::getParentid, id);
        List<Teachplan> teachplanList = teachplanMapper.selectList(lambdaQueryWrapper);
        // 来判断该课程计划下是否有子级
        if (!CollectionUtils.isEmpty(teachplanList)) {
            XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
        }
        //删除章节
        int flag = teachplanMapper.deleteById(id);
        //删除章节关联的
        LambdaQueryWrapper<TeachplanMedia> teachplanMediaLqw = new LambdaQueryWrapper<>();
        // TODO 还没有测试这个功能就是删除媒资
        teachplanMediaLqw.eq(TeachplanMedia::getTeachplanId, id);
        teachplanMediaMapper.delete(teachplanMediaLqw);
        if (flag <= 0) {
            XueChengPlusException.cast("课程计划删除失败！！");
        }
    }

    /**
     * 课程计划移动
     * @param id
     * @param moveType
     */
    @Transactional
    @Override
    public void moveUpOrDown(Long id, String moveType) {
        //根据id查询课程计划的信息然后根据课程计划
        Teachplan teachplan = teachplanMapper.selectById(id);
        //拿到移动对象的orderby的值
        Integer orderby = teachplan.getOrderby();
        //判断向上移动还是向下移动操作
        if (DictionaryConstant.MoveType.MOVE_UP.getDescription().equals(moveType)) {
            //拿出里面的parentId，找到比排序字段他小的课程计划 因为我们排序是以升序排序的
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper
                    .eq(Teachplan::getParentid, teachplan.getParentid())
                    .eq(Teachplan::getCourseId, teachplan.getCourseId())
                    .lt(Teachplan::getOrderby, orderby);
            List<Teachplan> teachplans = teachplanMapper.selectList(lambdaQueryWrapper);
            //根据条件找到交换目标对象
            Teachplan swapTarget = teachplans.stream()
                    .filter(item -> item.getOrderby() < orderby)
                    .max(Comparator.comparing(Teachplan::getOrderby))
                    .orElse(null);
            //与目标对象交换orderBy的值
            teachplan.setOrderby(swapTarget.getOrderby());
            swapTarget.setOrderby(orderby);
            //更新
            teachplanMapper.updateById(teachplan);
            teachplanMapper.updateById(swapTarget);
        } else if (DictionaryConstant.MoveType.MOVE_DOWN.getDescription().equals(moveType)) {
            //拿出里面的parentId，找到比排序字段他大的课程计划 因为我们排序是以升序排序的
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper
                    .eq(Teachplan::getParentid, teachplan.getParentid())
                    .eq(Teachplan::getCourseId, teachplan.getCourseId())
                    .gt(Teachplan::getOrderby, orderby);
            List<Teachplan> teachplans = teachplanMapper.selectList(lambdaQueryWrapper);
            //根据条件找到交换目标对象
            Teachplan swapTarget = teachplans.stream()
                    .filter(item -> item.getOrderby() > orderby)
                    .min(Comparator.comparing(Teachplan::getOrderby))
                    .orElse(null);
            //与目标对象交换orderBy的值
            teachplan.setOrderby(swapTarget.getOrderby());
            swapTarget.setOrderby(orderby);
            //更新
            teachplanMapper.updateById(teachplan);
            teachplanMapper.updateById(swapTarget);
        }
    }


    /**
     * 查询节点中order最大的值
     * @param teachplan
     * @return
     */
    private int getMaxOrderby(Teachplan teachplan) {
        // 查询节点中order最大的值然后，在最大值的基础上+1
        QueryWrapper<Teachplan> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(orderby)")
                .eq("parentid", teachplan.getParentid())
                .eq("course_id", teachplan.getCourseId());
        List<Object> objects = teachplanMapper.selectObjs(queryWrapper);
        //判断集合中是否有元素如果没有元素就指定一个orderby的固定值
        if (objects.get(0) == null) {
            return ORDERBY_INITIVAL_VALUE;
        }
        return (int) objects.get(0);
    }
}




