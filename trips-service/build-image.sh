#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/trips-service
docker build -t rms-backend/trips-service .