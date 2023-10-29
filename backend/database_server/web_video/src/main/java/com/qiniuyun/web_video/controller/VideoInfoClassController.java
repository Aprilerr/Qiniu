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

    @GetMapping("/class")
    public List<Integer> getClassByVideo(@RequestParam Integer videoId){

        return videoInfoClassService.getClassByVideo(videoId);
    }

    @GetMapping("/video")
    public List<Integer> getVideoByClass(@RequestParam Integer classId){
        return videoInfoClassService.getVideoByClass(classId);
    }

    @PostMapping
    public boolean createInfoClass(@RequestParam Integer videoId, @RequestParam Integer classId){

        VideoInfoClass videoInfoClass = new VideoInfoClass();
        videoInfoClass.setVideoId(videoId);
        videoInfoClass.setClassId(classId);
        return videoInfoClassService.createInfoClass(videoInfoClass);
    }

    @DeleteMapping
    public boolean deleteInfoClass(@RequestParam Integer videoId, @RequestParam Integer classId){
        VideoInfoClass videoInfoClass = new VideoInfoClass();
        videoInfoClass.setVideoId(videoId);
        videoInfoClass.setClassId(classId);
        return videoInfoClassService.deleteInfoClass(videoInfoClass);
    }

}
