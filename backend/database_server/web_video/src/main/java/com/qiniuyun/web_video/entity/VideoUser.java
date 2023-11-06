package com.qiniuyun.web_video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("video_user")
public class VideoUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    private String username;

    @TableField(value = "user_password")
    private String password;

    @TableField(value = "user_create_time")
    private String createTime;

    @TableField(value = "user_nickname")
    private String nickname;

    @TableField(value = "user_avatarUrl")
    private String avatarUrl;

    @TableField(value = "user_token")
    private String token;
}
