#!/bin/bash

# Docker 컨테이너 중지
docker stop Chatcode_Redis
docker stop Chatcode_DB

# Docker 컨테이너 삭제
docker rm Chatcode_DB
docker rm Chatcode_Redis

# Docker Compose로 서비스 시작
docker compose up -d

# 완료 메시지 출력
echo "Docker services have been restarted successfully."
