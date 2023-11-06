FROM python:3.11.0

WORKDIR /app/

COPY requirements.txt .

RUN pip install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple
RUN apt-get -y update
RUN apt-get install ffmpeg libsm6 libxext6  -y

COPY . .

CMD ["uvicorn", "app.main:app","--port", "8080", "--host", "0.0.0.0"]
