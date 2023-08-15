#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/alarms-service
docker build -t rms-backend/alarms-service .