package com.qiniuyun.web_video.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniuyun.web_video.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${oss.bucket_ts}")
    private String bucket_ts;

    @Value("${oss.bucket_m3u8}")
    private String bucket_m3u8;

    @Value("${oss.bucket_cover}")
    private String bucket_cover;

    @Value("${oss.cdn.prefix_m3u8}")
    private String prefix_m3u8;

    @Value("${oss.cdn.prefix_ts}")
    private String prefix_ts;

    @Value("${oss.cdn.prefix_cover}")
    private String prefix_cover;
    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }

    private String getUploadTokenTs() {
        return this.auth.uploadToken(bucket_ts, null, 3600, putPolicy);
    }
    private String getUploadTokenM3u8() {
        return this.auth.uploadToken(bucket_m3u8, null, 3600, putPolicy);
    }

    private String getUploadTokenCover() {
        return this.auth.uploadToken(bucket_cover, null, 3600, putPolicy);
    }

    @Override
    public String uploadFile(File file, String fileName) throws QiniuException {

        Response response = this.uploadManager.put(file, fileName, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, fileName, getUploadToken());
            retry++;
        }
        if (response.statusCode == 200) {
            return new StringBuffer().append(prefix_ts).append(fileName).toString();
        }
        return "上传失败!";
    }

    @Override
    public String uploadFile(InputStream inputStream, String fileName) throws QiniuException {

        if (fileName.endsWith(".m3u8")){
            Response response = this.uploadManager.put(inputStream, fileName, getUploadTokenM3u8(), null, null);
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(inputStream, fileName, getUploadTokenM3u8(), null, null);
                retry++;
            }
            System.out.println("addr==" + response.address);
            if (response.statusCode == 200) {
                return new StringBuffer().append(prefix_m3u8).append(fileName).toString();
            }
            return "上传失败!";
        }else if (fileName.endsWith(".ts")){
            Response response = this.uploadManager.put(inputStream, fileName, getUploadTokenTs(), null, null);
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(inputStream, fileName, getUploadTokenTs(), null, null);
                retry++;
            }
            System.out.println("addr==" + response.address);
            if (response.statusCode == 200) {
                return new StringBuffer().append(prefix_ts).append(fileName).toString();
            }
            return "上传失败!";
        }else {    // 上传图片
            Response response = this.uploadManager.put(inputStream, fileName, getUploadTokenCover(), null, null);
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(inputStream, fileName, getUploadTokenCover(), null, null);
                retry++;
            }
            System.out.println("addr==" + response.address);
            if (response.statusCode == 200) {
                return new StringBuffer().append(prefix_cover).append(fileName).toString();
            }
            return "上传失败!";

        }

    }

    @Override
    public String delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response.statusCode == 200 ? "删除成功!" : "删除失败!";
    }

    @Override
    public String downloadFile(String fileName) throws UnsupportedEncodingException {

        if (fileName.endsWith(".m3u8")){
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String publicUrl = String.format("%s%s", prefix_m3u8, encodedFileName);
            long expireInSeconds = 36000;//1小时，可以自定义链接过期时间
            String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
            return finalUrl;
        }else if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".bmp") ||
                fileName.endsWith(".gif") || fileName.endsWith(".jpeg") || fileName.endsWith(".webp") ){
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String finalUrl = String.format("%s%s", prefix_cover, encodedFileName);
            return finalUrl;
        }else if (fileName.endsWith(".ts")){
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String finalUrl = String.format("%s%s", prefix_ts, encodedFileName);
            return finalUrl;
        }
        return "下载失败";
    }


}
