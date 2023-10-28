# -*- coding: utf-8 -*-
from model.Recorder import Recorder, Precorder
from model.VideoInfo import VideoInfo
from model.TaskInfo import TaskInfo
import os
import json
import ffmpeg
import time
import random
from concurrent.futures import ProcessPoolExecutor
from utils.database_api import file_upload_api, info_upload_api
import base64
# 任务管理进程

def main():
    """
    任务管理主进程
    TODO: 任务管理与web服务器之间的通讯
    """
    print(f"Start task controller process...")
    record = Recorder()
    precord = Precorder()
    print(f"a point")
    with ProcessPoolExecutor(max_workers=10) as excutor:
        while True:
            print(f"task controller is running...")
            # 检查任务状态
            precord.check()
            print(f"b point")
            # 从任务队列中取出任务
            pop_task = record.is_empty_and_pop()
            print(f"c point")
            if  pop_task is None:
                print(f"Record list is empty, sleep 5 seconds")
                time.sleep(5)
                continue

            # 检查进程队列是否已满
            if precord.is_full():
                print(f"Process list is full, sleep 5 seconds")
                time.sleep(5)
                continue

            # 添加任务到进程队列
            precord.add(excutor.submit(test_process, pop_task))

def test_process(ti: TaskInfo):
    print(f"task : {ti.created_time} is running")
    ti.status = "PROCESSING"
    time.sleep(random.randint(1,10))
    ti.status = "DONE"
    print(f"task : {ti.created_time} is done. please check it")
    return ti


def subprocess(ti: TaskInfo):
    """
    视频处理线程函数
    ti: 任务信息
    """
    print(f"task : {ti.created_time} is running")
    # 获取项目根目录
    ipath = os.environ["IPATH"]
    print(f"ipath: {ipath}")
    # 获取暂存视频目录
    temp_path = os.path.join(ipath, "tmp")
    # 获得处理视频的目录
    video_path = os.path.join(temp_path, ti.created_time)
    print(f"video_path: {video_path}")
    # 读取视频信息
    with open(os.path.join(video_path, "info.json"), "r") as f:
        data = f.read()
    video_info = json.loads(data)
    video_info_obj = VideoInfo(**video_info)
    # 需要处理的视频文件
    video_file = os.path.join(video_path, video_info_obj.video)
    # 创建输出文件夹
    output_folder = os.path.join(video_path, "out")
    os.makedirs(output_folder, exist_ok=True)
    # 视频切片处理
    stream = ffmpeg.input(video_file,
                          f="mp4")
    stream = ffmpeg.output(stream, os.path.join(output_folder, os.path.splitext(ti.name)[0] + ".m3u8"),
                            force_key_frames="expr:gte(t,n_forced*5)",
                            hls_time=5,
                            vf="format=pix_fmts=yuv420p,scale=1280:720",
                            r="25",
                            vcodec="h264",
                            acodec="acc",
                            f="hls")
    stream.run()
    m3u8_file = os.path.join(output_folder, os.path.splitext(ti.name)[0] + ".m3u8")
    wrapper = os.popen(f"ffprobe -loglevel warning -show_format -of json \"{m3u8_file}\"")
    json_info = wrapper.read()
    data = json.loads(json_info)
    duration = float(data["format"]["duration"])
    size = data["format"]["size"]
    # 视频封面图处理
    stream = ffmpeg.input(m3u8_file)
    stream = ffmpeg.output(stream, os.path.join(output_folder, "cover.png"),f="image2",ss=1, vframes=1)
    stream.run()
    # 视频信息入库
    # TODO: 视频信息入库
    for file in os.listdir(output_folder):
        if file.endswith(".m3u8") or file.endswith(".png"):
            continue
        if file.endswith(".json"):
            with open(os.path.join(output_folder, ti.created_time + ".png"), "rb") as f:
                cover = f.read()
            cover_base64 = base64.b64encode(cover)
            info_upload_api(name=video_info_obj.video,
                            description=video_info_obj.description,
                            label=video_info_obj.label,
                            cover=cover_base64,
                            m3u8=os.join(output_folder, ti.created_time + ".m3u8"))
        else:
            with open(os.path.join(output_folder, file), "rb") as f:
                file_upload_api(file, f.read())

    # 清除tmp文件夹内容
    # TODO: 清除tmp文件夹内容
    return True
