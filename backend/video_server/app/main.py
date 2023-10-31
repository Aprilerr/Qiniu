# -*- coding: utf-8 -*-
from fastapi import FastAPI, UploadFile, File, Form
from model.Recorder import Recorder, Increater
from model.TaskInfo import TaskInfo
from model.VideoInfo import VideoInfo
from typing import Annotated, List
import os
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor
from pcontroller.main import test_process, subprocess
import datetime
import uvicorn
import json
from fastapi.responses import JSONResponse

app = FastAPI()

record = Recorder()

# 项目启动目录
os.environ["IPATH"] = os.getcwd()

@app.post("/video")
async def post_video(video: Annotated[str, Form(...)],
                     name: Annotated[str, Form(...)],
                     description: Annotated[str, Form(...)],
                     label: Annotated[str, Form(...)] = "",
                     type: List[str] = Form(...),
                     file: UploadFile = File(...)):
    """
    视频上传接口
    video: 视频文件名称
    name: 视频名称
    description: 视频简介
    label: 视频标签
    type: 视频类型
    file: 视频文件
    """
    # fastapi对获取到的Form中的List外面多包装了一层数组，拆解得到实际video_type， 不需要eval拆解，不知道是怎么回事
    video_type = type
    vi = VideoInfo(video=video, name=name, description=description, label=label, type=video_type)
    # 获取视频创建时间和任务id
    creater = Increater()
    id=creater.get_id()
    created_time = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
    # 获取项目根目录
    ipath = os.environ["IPATH"]
    # 获取暂存视频目录
    temp_path = os.path.join(ipath, "tmp")
    # 获取视频名称
    video_name = vi.video
    # 判断视频名称是否是mp4结尾，并且对视频名称进行重新定义
    if not video_name.endswith(".mp4"):
        return {"status": 1, "created_time": created_time}
    video_name = created_time + ".mp4"
    vi.video = video_name
    # 保存视频文件到暂存目录
    os.makedirs(os.path.join(temp_path, created_time), exist_ok=True)
    with open(os.path.join(temp_path, created_time, video_name), "wb+") as f:
        f.write(await file.read())
    # 保存视频信息到暂存目录
    with open(os.path.join(temp_path, created_time, "info.json"), "w+") as f:
        f.write(json.dumps(vi.__dict__))
    # 将视频信息添加到任务队列
    ti = TaskInfo(id=id, created_time=created_time, name=video_name, status="WAITING")
    if subprocess(ti):
        return {"status": 0, "created_time": created_time}
    return {"status": 1, "created_time": created_time}


@app.post("/test")
async def post_test(name:str):
    creater = Increater()
    created_time = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
    id = creater.get_id()
    ti = TaskInfo(id=id, created_time=created_time, name=name, status="WAITING")
    print(f"add task {ti.created_time} to record list")
    with ProcessPoolExecutor() as executor:
        executor.submit(test_process, ti)
    return {"status": 0, "created_time": created_time}
