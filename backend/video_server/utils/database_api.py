# -*- coding: utf-8 -*-

import requests
from config.config import BACKEND_URL

# 数据库访问

def file_upload_api(file_name:str, file: bytes):
    """
    文件上传oss服务器接口
    file_name: 文件名称
    file: 文件字符串数据
    """
    if BACKEND_URL == None:
        print("file_upload_api: BACKEND_URL is None")
        return
    url = BACKEND_URL + "/oss"
    resp = requests.post(url, files={"file": (file_name, file)})
    if resp.ok:
        data = resp.text
    else:
        raise Exception("Upload file failed")
    print(f"file_upload_api: {data}")
    return data

def info_upload_api(name, description, video_type, cover, m3u8):
    """
    视频信息上传接口
    name: 视频名称
    description: 视频简介
    video_type: 视频分类
    cover: 视频封面的base64编码
    m3u8: 视频m3u8文件名称
    """
    if BACKEND_URL == None:
        print("info_upload_api: BACKEND_URL is None")
        return
    url = BACKEND_URL + "/video"
    resp = requests.post(url, params={"classfication": video_type}, data={
        "videoName": name,
        "videoDescription": description,
        "videocover": cover,
        "videoM3u8": m3u8
    })
    if resp.ok:
        data = resp.text
    else:
        raise Exception(f"upload video info failed")
    print(f"info_upload_api: {data}")
    return data
