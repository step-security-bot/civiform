# syntax=docker/dockerfile:1
FROM python:3.11.1-slim

RUN useradd --create-home appuser --no-log-init

USER appuser

WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt --no-warn-script-location
COPY . .

EXPOSE 8000

CMD ["python", "app.py"]

