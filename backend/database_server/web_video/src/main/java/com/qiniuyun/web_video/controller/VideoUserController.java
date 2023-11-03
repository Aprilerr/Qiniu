package com.qiniuyun.web_video.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.qiniuyun.web_video.common.Constants;
import com.qiniuyun.web_video.common.Result;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.entity.VideoUserPassword;
import com.qiniuyun.web_video.service.VideoUserService;
import com.qiniuyun.web_video.service.impl.VideoUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class VideoUserController {

    @Autowired
    private VideoUserService videoUserService;

    @Autowired
    VideoUserServiceImpl videoUserServiceImpl;

    @PostMapping("/login")
    public Result login(@RequestBody VideoUser videoUser) {
        String username = videoUser.getUserName();
        String password = videoUser.getUserPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        VideoUser dto = videoUserService.login(videoUser);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody VideoUser videoUser){
        String username = videoUser.getUserName();
        String password = videoUser.getUserNickname();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(videoUserServiceImpl.register(videoUser));
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody VideoUser videoUser) {
        if (videoUser.getUserName() == null && videoUser.getUserPassword() == null) {  // 新增用户默认密码
            videoUser.setUserPassword( SecureUtil.md5("123"));
        }
        return Result.success(videoUserService.saveOrUpdate(videoUser));
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody VideoUserPassword videoUserPassword) {
        videoUserPassword.setPassword(SecureUtil.md5(videoUserPassword.getPassword()));
        videoUserPassword.setNewPassword(SecureUtil.md5(videoUserPassword.getNewPassword()));
        videoUserServiceImpl.updatePassword(videoUserPassword);
        return Result.success();
    }


}
