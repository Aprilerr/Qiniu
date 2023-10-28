package com.qiniuyun.web_video.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("video_classfication")
public class VideoClassfication {

    @TableId(value = "class_id", type = IdType.AUTO)
    public Integer classId;

    @TableField("class_name")
    public String className;

    @TableField("creat_time")
    public String creatTime;
}
