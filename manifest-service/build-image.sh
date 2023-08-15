#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/manifest-service
docker build -t rms-backend/manifest-service .