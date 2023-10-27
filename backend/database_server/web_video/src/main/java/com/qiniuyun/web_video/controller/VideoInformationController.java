package com.qiniuyun.web_video.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiniuyun.web_video.common.APIResponse;
import com.qiniuyun.web_video.common.ResponeCode;
import com.qiniuyun.web_video.entity.VideoInformation;
import com.qiniuyun.web_video.entity.VideoTs;
import com.qiniuyun.web_video.mapper.VideoInformationMapper;
import com.qiniuyun.web_video.mapper.VideoTsMapper;
import com.qiniuyun.web_video.service.VideoInformationService;
import com.qiniuyun.web_video.service.VideoTsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoInformationController {

    @Autowired
    VideoInformationService videoInformationService;

    @Autowired
    VideoInformationMapper videoInformationMapper;

    @Autowired
    VideoTsService videoTsService;

    @GetMapping
    public APIResponse  getVideoInformation(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize) {

        IPage<VideoInformation> page = new Page<>(pageNum,pageSize);

        return new APIResponse(ResponeCode.SUCCESS, videoInformationMapper.selectPage(page,null));
    }



}
