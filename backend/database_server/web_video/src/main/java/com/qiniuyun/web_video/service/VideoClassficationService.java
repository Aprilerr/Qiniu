package com.qiniuyun.web_video.service;

import com.qiniuyun.web_video.entity.VideoClassfication;

import java.util.List;

public interface VideoClassficationService {
    List<VideoClassfication> getClassificationList();

    VideoClassfication getClassificationById(Integer classId);

    VideoClassfication getClassificationByName(String className);
    boolean createClassification(VideoClassfication videoClassfication);
    boolean deleteClassification(Integer classId);

    boolean updateClassification(VideoClassfication videoClassfication);


}
