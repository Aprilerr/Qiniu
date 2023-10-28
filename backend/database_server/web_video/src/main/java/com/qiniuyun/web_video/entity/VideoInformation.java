package com.qiniuyun.web_video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("video_information")
public class VideoInformation implements Serializable {

    @TableId(value = "video_id",type = IdType.AUTO)
    private Integer videoId;

    @TableField(value = "video_name")
    private String videoName;

    @TableField(value = "video_description")
    private String videoDescription;

    @TableField(value = "video_label")
    private String videoLabel;

    @TableField(value = "video_create_time")
    private String videoCreateTime;

    @TableField(value = "video_like")
    private Integer videoLike;

    @TableField(value = "video_comment")
    private Integer videoComment;

    @TableField(value = "video_collect")
    private Integer videoCollect;

    @TableField(value = "video_cover")
    private String videocover;

    @TableField(value = "video_m3u8")
    private String videoM3u8;

}
