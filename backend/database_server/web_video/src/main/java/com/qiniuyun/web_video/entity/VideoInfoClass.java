package com.qiniuyun.web_video.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("video_info_class")
public class VideoInfoClass {

    @TableId("video_Id")
    public Integer videoId;

    @TableField("class_id")
    public Integer classId;
}
