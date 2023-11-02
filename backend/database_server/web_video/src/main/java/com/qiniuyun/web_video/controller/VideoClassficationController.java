package com.qiniuyun.web_video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiniuyun.web_video.entity.VideoClassfication;
import com.qiniuyun.web_video.service.VideoClassficationService;
import com.qiniuyun.web_video.service.impl.VideoClassficationServiceImpl;
import com.qiniuyun.web_video.service.impl.VideoInfoClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/class")
public class VideoClassficationController {

    @Autowired
    VideoClassficationService videoClassficationService;

    @Autowired
    VideoClassficationServiceImpl videoClassficationServiceImpl;

    /**
     * 该接口用于获得所有的分类信息
     *
     * @return 返回所有分类的List
     */
    @GetMapping
    public List<VideoClassfication> getClassificationList(){
        List<VideoClassfication> list =  videoClassficationService.getClassificationList();
        return list;
    }

    /**
     *  该接口用于创建分类
     * @param videoClassfication
     * @return  返回布尔值
     * @throws IOException
     */

    @PostMapping
    @Transactional
    public boolean create(VideoClassfication videoClassfication) throws IOException {
        boolean result = videoClassficationService.createClassification(videoClassfication);
        return result;
    }

    /**
     * 该接口用于根据classId删除分类
     * @param classId 分类的Id
     * @return 返回是否删除的布尔值
     */
    @DeleteMapping("/{classId}")
    public boolean delete(@PathVariable Integer classId){

        boolean result = videoClassficationService.deleteClassification(classId);

        return result;
    }

    /**
     * 该接口用于根据className删除分类
     * @param className 分类的名称
     * @return
     */
    @DeleteMapping
    public boolean deleteByClassName(@RequestParam String className){
        QueryWrapper<VideoClassfication> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_name",className);

        boolean result = videoClassficationServiceImpl.remove(queryWrapper);
        return result;
    }


    /**
     * 该接口用于更新分类
     * @param videoClassfication
     * @return
     * @throws IOException
     */
    @PutMapping
    @Transactional
    public boolean update(VideoClassfication videoClassfication) throws IOException {
        boolean result = videoClassficationService.updateClassification(videoClassfication);
        return result;
    }

}
