package com.qiniuyun.web_video.controller;

import com.qiniuyun.web_video.common.Constants;
import com.qiniuyun.web_video.common.Result;
import com.qiniuyun.web_video.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/oss")
@Api
public class OssController {

    @Autowired
    OssService ossService;

    @PostMapping
    public Result ossUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();

        String result = ossService.uploadFile(inputStream, filename);
        if (result.equals("上传失败!")){
            return Result.error(Constants.CODE_500, "上传失败!");
        }

        return Result.success(result);
    }

//    @GetMapping
//    public List<String> ossDownload(@RequestParam("fileName") List<String> fileName) throws IOException {
//
//        List<String> file = new ArrayList<>();
//        for (int i =0;i<fileName.size();i++){
//            file.add(ossService.downloadFile(fileName.get(i)));
//        }
//        return file;
//    }

    @GetMapping
    public String ossDownload(@RequestParam("fileName") String fileName) throws IOException {

        return ossService.downloadFile(fileName) ;
    }

    @DeleteMapping("/{fileName}")
    public String ossDelete(@PathVariable("fileName") String fileName) throws IOException {

        return ossService.delete(fileName);

    }
}
