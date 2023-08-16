#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/locations-service
docker build -t rms-backend/locations-service .