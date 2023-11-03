package com.qiniuyun.web_video.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniuyun.web_video.common.Constants;
import com.qiniuyun.web_video.controller.DTO.VideoUserDTO;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.controller.DTO.VideoUserPassword;
import com.qiniuyun.web_video.exception.ServiceException;
import com.qiniuyun.web_video.mapper.VideoUserMapper;
import com.qiniuyun.web_video.service.VideoUserService;
import com.qiniuyun.web_video.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoUserServiceImpl extends ServiceImpl<VideoUserMapper, VideoUser> implements VideoUserService{

    @Resource
    private VideoUserMapper videoUserMapper;

    @Override
    public VideoUserDTO login(VideoUserDTO videoUserDTO) {
        // 用户密码 md5加密
        videoUserDTO.setPassword(SecureUtil.md5(videoUserDTO.getPassword()));
        VideoUser one = getUserInfo(videoUserDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, videoUserDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getUserId().toString(), one.getPassword());
            videoUserDTO.setToken(token);

            return videoUserDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public VideoUser register(VideoUserDTO videoUserDTO) {
        // 用户密码 md5加密
        videoUserDTO.setPassword(SecureUtil.md5(videoUserDTO.getPassword()));
        VideoUser one = getUserInfo(videoUserDTO);
        if (one == null) {
            one = new VideoUser();
            BeanUtil.copyProperties(videoUserDTO, one, true);
            // 默认一个普通用户的角色
            if (one.getNickname() == null) {
                one.setNickname(one.getUsername());
            }
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public void updatePassword(VideoUserPassword videoUserPassword) {
        int update = videoUserMapper.updatePassword(videoUserPassword);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    private VideoUser getUserInfo(VideoUserDTO videoUserDTO) {
        QueryWrapper<VideoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", videoUserDTO.getUsername());
        queryWrapper.eq("user_password", videoUserDTO.getPassword());
        VideoUser one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
