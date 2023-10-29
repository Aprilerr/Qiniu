package com.qiniuyun.web_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qiniuyun.web_video.entity.VideoInfoClass;
import com.qiniuyun.web_video.mapper.VideoInfoClassMapper;
import com.qiniuyun.web_video.service.VideoInfoClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoInfoClassServiceImpl extends ServiceImpl<VideoInfoClassMapper, VideoInfoClass> implements VideoInfoClassService {

    @Autowired
    VideoInfoClassMapper videoInfoClassMapper;

    public List<Integer> getVideoByClass(Integer classId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("class_id", classId);
        List<VideoInfoClass> list = videoInfoClassMapper.selectList(queryWrapper);         // 查询到所有实体类的列表
        List<Integer> videoIdList = list.stream().map(VideoInfoClass::getVideoId).collect(Collectors.toList());
        return videoIdList;

    }

    public List<Integer> getClassByVideo(Integer videoId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("video_id", videoId);
        List<VideoInfoClass> list = videoInfoClassMapper.selectList(queryWrapper);
        List<Integer> classIdList = list.stream().map(VideoInfoClass::getClassId).collect(Collectors.toList());
        return classIdList;
    }

    public boolean createInfoClass(VideoInfoClass videoInfoClass){

        QueryWrapper queryWrapper = new QueryWrapper<VideoInfoClass>();
        queryWrapper.eq("video_id", videoInfoClass.getVideoId());
        queryWrapper.eq("class_id", videoInfoClass.getClassId());
        boolean noExist = SqlHelper.retBool(videoInfoClassMapper.selectCount(queryWrapper));

        if (!noExist){
            boolean result = SqlHelper.retBool(videoInfoClassMapper.insert(videoInfoClass));
            return result;
        }else {
            boolean result = !noExist;
            return result;
        }
    }

    public boolean deleteInfoClass(VideoInfoClass videoInfoClass){

        QueryWrapper queryWrapper = new QueryWrapper<VideoInfoClass>();
        queryWrapper.eq("video_id", videoInfoClass.getVideoId());
        queryWrapper.eq("class_id", videoInfoClass.getClassId());

        boolean result = SqlHelper.retBool(videoInfoClassMapper.delete(queryWrapper));
        return result;
    }


}
