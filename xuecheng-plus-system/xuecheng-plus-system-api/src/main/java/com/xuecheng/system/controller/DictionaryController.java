package com.xuecheng.system.controller;

import com.xuecheng.system.model.po.Dictionary;
import com.xuecheng.system.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典 前端控制器
 *
 * @author itcast
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class DictionaryController  {

    @Autowired
    private DictionaryService  dictionaryService;

    /**
     * 获取搜索字典项
     * @return
     */
    @GetMapping("/dictionary/all")
    public List<Dictionary> queryAll() {
        return dictionaryService.queryAll();
    }

    /**
     * 按字典代码获得字典项
     * @param code
     * @return
     */
    @GetMapping("/dictionary/code/{code}")
    public Dictionary getByCode(@PathVariable String code) {
        return dictionaryService.getByCode(code);
    }
}
