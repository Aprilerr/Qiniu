package com.qiniuyun.web_video.controller;

import com.qiniuyun.web_video.entity.VideoClassfication;
import com.qiniuyun.web_video.service.VideoClassficationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/class")
public class VideoClassficationController {

    @Autowired
    VideoClassficationService videoClassficationService;

    @GetMapping
    public List<VideoClassfication> getClassificationList(){
        List<VideoClassfication> list =  videoClassficationService.getClassificationList();
        return list;
    }


    @PostMapping
    @Transactional
    public boolean create(VideoClassfication videoClassfication) throws IOException {
        boolean result = videoClassficationService.createClassification(videoClassfication);
        return result;
    }


    @DeleteMapping("/{classId}")
    public boolean delete(@PathVariable Integer classId){

        boolean result = videoClassficationService.deleteClassification(classId);

        return result;
    }


    @PutMapping
    @Transactional
    public boolean update(VideoClassfication videoClassfication) throws IOException {
        boolean result = videoClassficationService.updateClassification(videoClassfication);
        return result;
    }

}
