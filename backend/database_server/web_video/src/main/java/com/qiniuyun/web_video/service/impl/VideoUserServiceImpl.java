package com.qiniuyun.web_video.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniuyun.web_video.common.Constants;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.entity.VideoUserPassword;
import com.qiniuyun.web_video.exception.ServiceException;
import com.qiniuyun.web_video.mapper.VideoUserMapper;
import com.qiniuyun.web_video.service.VideoUserService;
import com.qiniuyun.web_video.utils.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoUserServiceImpl extends ServiceImpl<VideoUserMapper, VideoUser> implements VideoUserService{

    @Resource
    private VideoUserMapper videoUserMapper;

    @Override
    public VideoUser login(VideoUser videoUser) {
        // 用户密码 md5加密
        videoUser.setUserPassword(SecureUtil.md5(videoUser.getUserPassword()));
        VideoUser one = getUserInfo(videoUser);
        if (one != null) {
            BeanUtil.copyProperties(one, videoUser, true);
            // 设置token
            String token = TokenUtils.genToken(one.getUserId().toString(), one.getUserPassword());
            videoUser.setUserToken(token);

            return videoUser;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public VideoUser register(VideoUser videoUser) {
        // 用户密码 md5加密
        videoUser.setUserPassword(SecureUtil.md5(videoUser.getUserPassword()));
        VideoUser one = getUserInfo(videoUser);
        if (one == null) {
            one = new VideoUser();
            BeanUtil.copyProperties(videoUser, one, true);
            // 默认一个普通用户的角色
            if (one.getUserNickname() == null) {
                one.setUserNickname(one.getUserName());
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

    private VideoUser getUserInfo(VideoUser videoUser) {
        QueryWrapper<VideoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", videoUser.getUserName());
        queryWrapper.eq("password", videoUser.getUserPassword());
        VideoUser one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
