# -*- coding: utf-8 -*-
# 命令行启动文件

import argparse
import os
import ffmpeg
import json
import datetime

if __name__ == "__main__":
    argparser = argparse.ArgumentParser(description="Video Server")
    # 获取视频地址
    argparser.add_argument("-v", "--video_path", type=str, help="video path", required=True)
    # 获取视频名称
    argparser.add_argument("-n", "--name", type=str, help="video name", required=True)
    # 获取视频简介
    argparser.add_argument("-d", "--description", type=str, help="video description", required=True)
    # 获取视频标签
    argparser.add_argument("-l", "--label", type=str, help="video label, with comma seperation, like: \" catoon, action, etc\"", default="")
    # 获取视频分类
    argparser.add_argument("-t", "--type", type=str, help="video type, must follow some rules", default="")
    # 获取输出文件夹
    argparser.add_argument("-o", "--output", type=str, help="output folder", default="./out")

    args = argparser.parse_args()

    # 判断视频是否存在，并且符合格式规范
    # TODO: 视频规范只支持mp4格式，后续可以考虑增加其他格式
    if not args.video_path.endswith(".mp4") or not os.path.exists(args.video_path):
        print("Video path is not correct, please check it again")
        exit(1)

    # 判断视频分类是否符合规范
    # TODO: 规范待定

    # 创建输出文件夹
    output_folder = os.path.join(args.output, os.path.splitext(os.path.basename(args.video_path))[0])
    os.makedirs(output_folder, exist_ok=True)

    # 视频切片处理

    stream = ffmpeg.input(args.video_path,
                          f="mp4")
    stream = ffmpeg.output(stream, os.path.join(output_folder, os.path.splitext(os.path.basename(args.video_path))[0] + ".m3u8"),
                            force_key_frames="expr:gte(t,n_forced*5)",
                            hls_time=5,
                            vf="format=pix_fmts=yuv420p,scale=1280:720",
                            r="25",
                            vcodec="h264",
                            acodec="acc",
                            f="hls")
    stream.run()

    # 视频信息获取

    m3u8_file = os.path.join(output_folder, os.path.splitext(os.path.basename(args.video_path))[0]) + ".m3u8"
    wrapper = os.popen(f"ffprobe -loglevel warning -show_format -of json \"{m3u8_file}\"")
    json_info = wrapper.read()
    print(f"hello: \n {json_info}")
    data = json.loads(json_info)
    duration = float(data["format"]["duration"])
    size = data["format"]["size"]

    # 视频封面图处理

    stream = ffmpeg.input(m3u8_file)
    stream = ffmpeg.output(stream, os.path.join(output_folder, "cover.png"),f="image2",ss=1, vframes=1)
    stream.run()

    # 视频信息入库

    video_info = {
        "name": args.name,
        "description": args.description,
        "label": args.label,
        "type": args.type,
        "duration": duration,
        "size": size,
        "create_time": datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
    }

    print(f"Video info: {video_info}")

    with open(os.path.join(output_folder, "info.json"), "w+", encoding="utf-8") as f:
        f.write(json.dumps(video_info))
