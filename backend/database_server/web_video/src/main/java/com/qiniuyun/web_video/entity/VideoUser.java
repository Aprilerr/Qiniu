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
    private String userName;

    @TableField(value = "user_password")
    private String userPassword;

    @TableField(value = "user_create_time")
    private String userCreateTime;

    @TableField(value = "user_nickname")
    private String userNickname;

    @TableField(value = "user_avatarUrl")
    private String userAvatarUrl;
}
