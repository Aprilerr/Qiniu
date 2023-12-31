package com.qiniuyun.web_video.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.qiniuyun.web_video.common.Constants;
import com.qiniuyun.web_video.common.Result;
import com.qiniuyun.web_video.controller.DTO.VideoUserDTO;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.controller.DTO.VideoUserPassword;
import com.qiniuyun.web_video.service.VideoUserService;
import com.qiniuyun.web_video.service.impl.VideoUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class VideoUserController {

    @Autowired
    private VideoUserService videoUserService;

    @Autowired
    VideoUserServiceImpl videoUserServiceImpl;

    @PostMapping("/login")
    public Result login(@RequestBody VideoUserDTO videoUserDTO) {
        String username = videoUserDTO.getUsername();
        String password = videoUserDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        VideoUserDTO dto = videoUserService.login(videoUserDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody VideoUserDTO videoUserDTO ){
        String username = videoUserDTO.getUsername();
        String password = videoUserDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(videoUserService.register(videoUserDTO));
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody VideoUser videoUser) {
        if (videoUser.getUserId() == null && videoUser.getPassword() == null) {  // 新增用户默认密码
            videoUser.setPassword( SecureUtil.md5("123"));
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

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable Integer userId) {
        return Result.success(videoUserService.removeById(userId));
    }

    @GetMapping("/{userId}")
    public Result findOne(@PathVariable Integer userId) {
        return Result.success(videoUserService.getById(userId ));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<VideoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return Result.success(videoUserService.getOne(queryWrapper));
    }

}
