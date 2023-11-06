package com.qiniuyun.web_video.controller.DTO;

import lombok.Data;

@Data
public class VideoUserPassword {
    private String username;
    private String password;
    private String newPassword;
}
