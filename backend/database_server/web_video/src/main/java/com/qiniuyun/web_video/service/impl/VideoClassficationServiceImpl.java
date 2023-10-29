package com.qiniuyun.web_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qiniuyun.web_video.entity.VideoClassfication;
import com.qiniuyun.web_video.mapper.VideoClassficationMapper;
import com.qiniuyun.web_video.service.VideoClassficationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoClassficationServiceImpl extends ServiceImpl<VideoClassficationMapper, VideoClassfication> implements VideoClassficationService {
    @Autowired
    VideoClassficationMapper videoClassficationMapper;

    @Override
    public List<VideoClassfication> getClassificationList(){
        return  videoClassficationMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public  VideoClassfication getClassificationById(Integer classId){
        return videoClassficationMapper.selectById(classId);
    }

    @Override
    public VideoClassfication getClassificationByName(String className){
        QueryWrapper<VideoClassfication> queryWrapper = new QueryWrapper<VideoClassfication>();
        queryWrapper.eq("class_name", className);

        return videoClassficationMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean createClassification(VideoClassfication videoClassfication){
        videoClassfication.setCreatTime(String.valueOf(System.currentTimeMillis()));
        return  SqlHelper.retBool(videoClassficationMapper.insert(videoClassfication));
    }

    @Override
    public boolean deleteClassification(Integer classId){
        return  SqlHelper.retBool(videoClassficationMapper.deleteById(classId));
    }

    @Override
    public boolean updateClassification(VideoClassfication videoClassfication){
        videoClassfication.setCreatTime(String.valueOf(System.currentTimeMillis()));
        return  SqlHelper.retBool(videoClassficationMapper.updateById(videoClassfication));
    }

}
