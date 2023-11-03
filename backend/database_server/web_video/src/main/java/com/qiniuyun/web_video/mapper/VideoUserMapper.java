package com.qiniuyun.web_video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.entity.VideoUser;
import com.qiniuyun.web_video.entity.VideoUserPassword;
import org.apache.ibatis.annotations.Update;

public interface VideoUserMapper extends BaseMapper<VideoUser> {
    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(VideoUserPassword videoUserPassword);

}
