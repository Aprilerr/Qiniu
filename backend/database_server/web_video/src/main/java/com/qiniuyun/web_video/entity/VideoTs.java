package com.qiniuyun.web_video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@TableName("video_m3u8")
@Component
public class VideoTs {

    @TableField(value = "video_id")
    private Integer videoId;

    @TableField("video_ts")
    private String  tsName;

}
