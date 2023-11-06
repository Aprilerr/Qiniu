package com.qiniuyun.web_video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.controller.DTO.VideoUserPassword;
import org.apache.ibatis.annotations.Update;

public interface VideoUserMapper extends BaseMapper<VideoUser> {
    @Update("update video_user set user_password = #{newPassword} where user_name = #{username} and user_password = #{password}")
    int updatePassword(VideoUserPassword videoUserPassword);

}
