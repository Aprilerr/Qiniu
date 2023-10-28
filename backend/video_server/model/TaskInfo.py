# -*- coding: utf-8 -*-

from pydantic import BaseModel

# 视频处理任务信息

class TaskInfo(BaseModel):
    """
    id: 任务id
    created_time: 任务创建时间
    name: 视频名称
    status: 任务状态
    """
    id: int
    created_time: str
    name: str
    status: str
