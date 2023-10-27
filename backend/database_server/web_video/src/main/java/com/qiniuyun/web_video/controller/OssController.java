package com.qiniuyun.web_video.controller;

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
    public String ossUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return "error";
        }
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();

        return ossService.uploadFile(inputStream, filename);
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
