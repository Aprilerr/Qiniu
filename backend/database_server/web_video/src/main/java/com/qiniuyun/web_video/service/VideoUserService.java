package com.qiniuyun.web_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.entity.VideoUserPassword;
import com.qiniuyun.web_video.mapper.VideoUserMapper;

public interface VideoUserService extends IService<VideoUser> {

    VideoUser register(VideoUser videoUser);

    VideoUser login(VideoUser videoUser);

    void updatePassword(VideoUserPassword videoUserPassword);
}
