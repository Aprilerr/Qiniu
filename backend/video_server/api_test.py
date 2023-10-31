# -*- coding: utf-8 -*-
from utils.database_api import file_upload_api, info_upload_api
import os
import json

if __name__ == "__main__":

    # 测试文件上传功能

    # print(f"start to upload file")
    # with open("./local.resource/production_id_4772983 (2160p)0.ts", "rb") as f:
    #     file_name = "production_id_4772983 (2160p)0.ts"
    #     file_bytes = f.read()
    # resp = file_upload_api(file_name, file_bytes)
    # print(f"get response of: {resp}")

    # 测试视频信息上传功能
    print(f"start to upload video info")
    with open("./local.resource/cover.png", "rb") as f:
        cover = f.read()
    with open("./local.resource/info.json", "r") as f:
        info_json = f.read()
    try:
        info_obj = json.loads(info_json)
        print(f"info_obj: {info_obj}")
    except Exception as e:
        print(f"{e}")
    m3u8_name = "production_id_4772983 (2160p).m3u8"
    resp = info_upload_api(info_obj.get("name"), info_obj.get("description"), info_obj.get("type"), cover, m3u8_name)
    print(f"get respones of: {resp}")
