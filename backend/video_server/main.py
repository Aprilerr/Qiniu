# -*- coding: utf-8 -*-
# 命令行启动文件

import argparse
import os
import ffmpeg

if __name__ == "__main__":
    argparser = argparse.ArgumentParser(description="Video Server")
    # 获取视频地址
    argparser.add_argument("-v", "--video_path", type=str, help="video path", required=True)
    # 获取视频名称
    argparser.add_argument("-n", "--name", type=str, help="video name", required=True)
    # 获取视频简介
    argparser.add_argument("-d", "--description", type=str, help="video description", required=True)
    # 获取视频标签
    argparser.add_argument("-t", "--tag", type=str, help="video tag, with comma seperation, like: \" catoon, action, etc\"", default="")
    # 获取视频分类
    argparser.add_argument("-c", "--category", type=str, help="video category, must follow some rules", default="")
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
                          force_key_frames="expr:gte(t,n_forced*5)",
                          hls_time=5,
                          vf="format=pix_fmts=yuv420p,scale=1280:720",
                          r="25",
                          vcodec="h264",
                          acodec="acc")
    stream = ffmpeg.output(stream, os.path.join(output_folder, "index.m3u8"), f="hls")
    stream.run()

    # 视频封面图处理

    # 视频信息入库

    #
