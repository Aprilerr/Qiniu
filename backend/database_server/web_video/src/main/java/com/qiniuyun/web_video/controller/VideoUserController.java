package com.qiniuyun.web_video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.service.VideoUserService;
import com.qiniuyun.web_video.service.impl.VideoUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class VideoUserController {

    @Autowired
    private VideoUserService videoUserService;

    @Autowired
    VideoUserServiceImpl videoUserServiceImpl;

    @PostMapping
    public String AddUser(@RequestParam VideoUser videoUser){
        QueryWrapper<VideoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", videoUser.getUserName());
        if (SqlHelper.retBool(videoUserServiceImpl.count(queryWrapper))){
            return "该账号已存在";
        }
        videoUserService.save(videoUser);
        return "注册成功";
    }



}
