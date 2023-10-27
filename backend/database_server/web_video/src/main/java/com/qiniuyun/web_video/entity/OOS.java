package com.qiniuyun.web_video.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "oss")
public class OOS {
    // 读取的时候，要创建成员变量（变量名要和application.xml文件中的名字一致）
    // 并且 成员变量必须创建setter方法，进行自动赋值  （自动读取配置文件赋值）
    private String accessKey;

    private String secretKey;

    private String bucket;

}
