package com.qiniuyun.web_video.service;

import com.qiniuyun.web_video.entity.VideoTs;

import java.util.List;

public interface VideoTsService {

    List<VideoTs>  selectByVideoId(Integer videoId);
 }
