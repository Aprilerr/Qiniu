package com.qiniuyun.web_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.mapper.VideoUserMapper;
import com.qiniuyun.web_video.service.VideoUserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class VideoUserServiceImpl extends ServiceImpl<VideoUserMapper, VideoUser> implements VideoUserService{

}
