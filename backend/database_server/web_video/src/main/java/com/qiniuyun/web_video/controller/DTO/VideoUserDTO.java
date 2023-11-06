package com.qiniuyun.web_video.controller.DTO;

import lombok.Data;

@Data
public class VideoUserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
}
