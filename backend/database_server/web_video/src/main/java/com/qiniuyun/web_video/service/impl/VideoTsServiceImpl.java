package com.qiniuyun.web_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.mapper.VideoTsMapper;
import com.qiniuyun.web_video.service.VideoTsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTsServiceImpl extends ServiceImpl<VideoTsMapper, VideoTs> implements VideoTsService {


    @Autowired
    VideoTsMapper videoTsMapper;

    public List<VideoTs> selectByVideoId(Integer videoId) {

        QueryWrapper<VideoTs> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);

        return videoTsMapper.selectList(queryWrapper);
    }
}
