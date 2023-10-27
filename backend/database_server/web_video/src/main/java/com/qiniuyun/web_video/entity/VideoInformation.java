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

    @TableField(value = "name")
    private String videoName;

    @TableField(value = "description")
    private String videoDescription;

    @TableField(value = "label")
    private String videoLabel;

    @TableField(value = "create_time")
    private String videoCreateTime;

    @TableField(value = "like")
    private String videoLike;

    @TableField(value = "comment")
    private String videoComment;

    @TableField(value = "collect")
    private String videoCollect;

    @TableField(value = "cover")
    private String videocover;

}
