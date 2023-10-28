package com.qiniuyun.web_video.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiniuyun.web_video.common.APIResponse;
import com.qiniuyun.web_video.common.ResponeCode;
import com.qiniuyun.web_video.entity.OOS;
import com.qiniuyun.web_video.entity.VideoInformation;
import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.mapper.VideoInformationMapper;
import com.qiniuyun.web_video.mapper.VideoTsMapper;
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
    VideoTsService videoTsService;

    @Autowired
    OssController ossController = new OssController();


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

    @PutMapping
    public String addVideoInformation(@RequestBody VideoInformation videoInformation) {

        videoInformation.setVideoLike(0);
        videoInformation.setVideoComment(0);
        videoInformation.setVideoCollect(0);
        videoInformationService.save(videoInformation);
        return "sucess";
    }






}
