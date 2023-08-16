#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/zones-service
docker build -t rms-backend/zones-service .