package com.qiniuyun.web_video.controller;

import com.qiniuyun.web_video.entity.VideoInfoClass;
import com.qiniuyun.web_video.service.VideoInfoClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videoclass")
public class VideoInfoClassController {

    @Autowired
    VideoInfoClassService videoInfoClassService;

    /**
     * 根据视频获取其分类信息
     * @param videoId 视频id
     * @return 分类信息
     */
    @GetMapping("/class")
    public List<Integer> getClassByVideo(@RequestParam Integer videoId){

        return videoInfoClassService.getClassByVideo(videoId);
    }

    /**
     * 根据分类获取其视频id
     * @param classId 分类id
     * @return 视频id
     */
    @GetMapping("/video")
    public List<Integer> getVideoByClass(@RequestParam Integer classId){
        return videoInfoClassService.getVideoByClass(classId);
    }

    /**
     * 新建视频的分类信息
     * @param videoId 视频Id
     * @param classId 分类id
     * @return
     */
    @PostMapping
    public boolean createInfoClass(@RequestParam Integer videoId, @RequestParam Integer classId){

        VideoInfoClass videoInfoClass = new VideoInfoClass();
        videoInfoClass.setVideoId(videoId);
        videoInfoClass.setClassId(classId);
        return videoInfoClassService.createInfoClass(videoInfoClass);
    }

    /**
     * 删除视频的分类信息
     * @param videoId 视频Id
     * @param classId 分类id
     * @return
     */
    @DeleteMapping
    public boolean deleteInfoClass(@RequestParam Integer videoId, @RequestParam Integer classId){
        VideoInfoClass videoInfoClass = new VideoInfoClass();
        videoInfoClass.setVideoId(videoId);
        videoInfoClass.setClassId(classId);
        return videoInfoClassService.deleteInfoClass(videoInfoClass);
    }

}
