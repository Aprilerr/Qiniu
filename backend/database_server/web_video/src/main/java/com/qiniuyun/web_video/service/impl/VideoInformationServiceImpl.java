package com.qiniuyun.web_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniuyun.web_video.entity.VideoInfoClass;
import com.qiniuyun.web_video.entity.VideoInformation;
import com.qiniuyun.web_video.mapper.VideoInfoClassMapper;
import com.qiniuyun.web_video.mapper.VideoInformationMapper;
import com.qiniuyun.web_video.service.VideoInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoInformationServiceImpl extends ServiceImpl<VideoInformationMapper, VideoInformation> implements VideoInformationService {

    @Autowired
    VideoInfoClassMapper videoInfoClassMapper;

    public List<Integer> getClassByVideo(Integer videoId){
        QueryWrapper queryWrapper = new  QueryWrapper<VideoInfoClass>();
        queryWrapper.eq("video_id",videoId);
        return videoInfoClassMapper.selectList(queryWrapper);
    }

    public List<VideoInformation> getVideoByClass(Integer classId){
        QueryWrapper queryWrapper = new  QueryWrapper<VideoInformation>();
        queryWrapper.eq("class_id",classId);
        return videoInfoClassMapper.selectList(queryWrapper);
    }


}
