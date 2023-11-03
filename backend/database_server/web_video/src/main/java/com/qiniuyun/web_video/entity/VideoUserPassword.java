package com.qiniuyun.web_video.entity;

import lombok.Data;

@Data
public class VideoUserPassword {
    private String username;
    private String password;
    private String newPassword;
}
