package com.qiniuyun.web_video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiniuyun.web_video.common.APIResponse;
import com.qiniuyun.web_video.common.ResponeCode;
import com.qiniuyun.web_video.entity.*;
import com.qiniuyun.web_video.mapper.VideoInformationMapper;
import com.qiniuyun.web_video.mapper.VideoTsMapper;
import com.qiniuyun.web_video.service.VideoClassficationService;
import com.qiniuyun.web_video.service.VideoInfoClassService;
import com.qiniuyun.web_video.service.VideoInformationService;
import com.qiniuyun.web_video.service.VideoTsService;
import javassist.compiler.ast.Variable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoInformationController {

    @Autowired
    VideoInformationService videoInformationService;

    @Autowired
    VideoInformationMapper videoInformationMapper;

    @Autowired
    VideoInfoClassService videoInfoClassService;

    @Autowired
    VideoClassficationService videoClassficationService;

    @Autowired
    VideoTsService videoTsService;

    @Autowired
    OssController ossController = new OssController();


    /**
     * 获取视频信息
     * @param pageNum 分页数字
     * @param pageSize 分页规模
     * @return 视频的List
     */
    @GetMapping
    public List<VideoInformation>  getVideoInformation(@RequestParam("pageNum") Integer pageNum,
                                                        @RequestParam("pageSize") Integer pageSize) {

        IPage<VideoInformation> page = new Page<>(pageNum,pageSize);

        List<VideoInformation> data = videoInformationService.page(page).getRecords();

        return data;
    }

    @GetMapping("/{videoId}")
    public Map<String, Object> getVideoInformationById(@PathVariable("videoId") String videoId) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("videoInformation", videoInformationService.getById(videoId));

        String videoM3u8 = videoInformationService.getById(videoId).getVideoM3u8();
        try {
            String videoM3u8Url = ossController.ossDownload(videoM3u8);
            map.put("m3u8Url", videoM3u8Url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @PostMapping
    public boolean addVideoInformation(VideoInformation videoInformation,
                                       @RequestParam("classfication") List<String> classfication) {

        videoInformation.setVideoLike(0);
        videoInformation.setVideoComment(0);
        videoInformation.setVideoCollect(0);
        videoInformation.setVideoPlayVolume(0);
        videoInformation.setVideoCreateTime(String.valueOf(System.currentTimeMillis()));
        boolean result =  videoInformationService.save(videoInformation);


        // 将视频分类信息存入数据库中

        System.out.println(classfication);
        List<VideoInfoClass> video_class = new ArrayList<VideoInfoClass>();

        for (int i = 0; i < classfication.size(); i++){
            String className = classfication.get(i);
            VideoClassfication videoClassfication = videoClassficationService.getClassificationByName(className);   // 根据分类名获取其classId的实体类
            Integer classId = videoClassfication.getClassId();                                                      // 获取实体类中的classId
            VideoInfoClass videoInfoClass = new VideoInfoClass();
            videoInfoClass.setClassId(classId);
            videoInfoClass.setVideoId(videoInformation.getVideoId());
            video_class.add(videoInfoClass);
        }

        for (int j=0;j<video_class.size();j++){
            videoInfoClassService.createInfoClass(video_class.get(j));
        }

        return result;
    }







}
