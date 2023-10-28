# -*- coding: utf-8 -*-

from pydantic import BaseModel

# 视频信息类

class VideoInfo(BaseModel):
    """
    video: 视频文件名称，包含后缀
    name: 视频名称
    description: 视频描述
    label: 视频标签
    type: 视频类型
    """
    video: str
    name: str
    description: str
    label: str = ""
    type: str = ""
