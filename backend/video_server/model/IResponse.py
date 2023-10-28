# -*- coding: utf-8 -*-

from pydantic import BaseModel

class IResponse(BaseModel):
    msg: str = ""
    status: int = 0
    data: any = None
