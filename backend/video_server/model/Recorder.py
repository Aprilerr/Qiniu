# -*- coding: utf-8 -*-
import queue
from threading import Lock
def singleton(cls):
    _instance = {}

    def inner():
        if cls not in _instance:
            _instance[cls] = cls()
        return _instance[cls]
    return inner

# 单例模式，视频处理队列类
@singleton
class Recorder:
    def __init__(self):
        self.__recorder = queue.Queue()
        self.__lock = Lock()

    def add_tast(self, task):
        self.__recorder.put(task)

    def is_empty_and_pop(self):
        self.__lock.acquire()
        if self.__recorder.empty():
            self.__lock.release()
            return None
        else:
            task = self.__recorder.get()
            self.__lock.release()
            return task

# 单例模式，进程处理队列
@singleton
class Precorder:
    def __init__(self, max_size=2):
        """
        max_size: 最大进程数
        """
        self.__precorder = []
        self.__max_size = max_size


    def check(self):
        """
        查看处理队列中的进程是否处理完毕，如果处理完毕则删除进程
        """
        for i in range(len(self.__precorder)):
            if self.__precorder[i].done():
                print(f"process {self.__precorder[i].result().created_time} is done and remove it from process list.")
                self.__precorder.pop(i)
                continue

    def is_full(self):
        """
        返回处理队列是否已满
        """
        return len(self.__precorder) >= self.__max_size

    def add(self, process):
        """
        添加进程到处理队列，添加对象为future对象
        """
        self.__precorder.append(process)

@singleton
class Increater:
    def __init__(self):
        self.__id = 0
        self.__lock = Lock()

    def get_id(self):
        self.__lock.acquire()
        self.__id += 1
        id = self.__id
        self.__lock.release()
        return id
