# -*- coding: utf-8 -*-

import dotenv
import os

dotenv.load_dotenv()

BACKEND_URL = os.getenv("BACKEND_URL")
