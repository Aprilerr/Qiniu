package com.qiniuyun.web_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiniuyun.web_video.controller.DTO.VideoUserDTO;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.controller.DTO.VideoUserPassword;

public interface VideoUserService extends IService<VideoUser> {

    VideoUser register(VideoUserDTO videoUserDTO);

    VideoUserDTO login(VideoUserDTO videoUserDTO);

    void updatePassword(VideoUserPassword videoUserPassword);
}
