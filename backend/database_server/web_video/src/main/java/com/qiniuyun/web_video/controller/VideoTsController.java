package com.qiniuyun.web_video.controller;

import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.service.VideoTsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ts")
public class VideoTsController {

    @Autowired
    VideoTsService videoTsService;

    /**
     * 根据视频id获取视频切片信息
     * @param videoId 视频id
     * @return 视频切片信息
     */
    @GetMapping
    public List<VideoTs> getVideoTsName(@RequestParam("videoId") Integer videoId){

        List<VideoTs> videoTsList = videoTsService.selectByVideoId(videoId);

        return videoTsList;
    }

    @PostMapping
    public boolean saveVideoTs(@RequestParam VideoTs videoTs){
        boolean result = videoTsService.savaVideoTs(videoTs);
        return result;
    }

}
