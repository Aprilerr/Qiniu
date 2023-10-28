package com.qiniuyun.web_video.controller;

import com.qiniuyun.web_video.common.APIResponse;
import com.qiniuyun.web_video.common.ResponeCode;
import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.service.VideoTsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ts")
public class VideoTsController {

    @Autowired
    VideoTsService videoTsService;

    @GetMapping
    public List<VideoTs> getVideoTsName(@RequestParam("videoId") Integer videoId){

        List<VideoTs> videoTsList = videoTsService.selectByVideoId(videoId);


        return videoTsList;
    }

}
