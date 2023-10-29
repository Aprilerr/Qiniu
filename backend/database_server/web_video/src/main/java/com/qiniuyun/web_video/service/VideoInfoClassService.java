package com.qiniuyun.web_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiniuyun.web_video.entity.VideoInfoClass;

import java.util.List;

public interface VideoInfoClassService extends IService<VideoInfoClass> {
    List<Integer> getClassByVideo(Integer videoId);

    List<Integer> getVideoByClass(Integer classId);

    boolean createInfoClass(VideoInfoClass videoInfoClass);

    boolean deleteInfoClass(VideoInfoClass videoInfoClass);

}
