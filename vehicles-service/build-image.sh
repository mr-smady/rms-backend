#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/vehicles-service
docker build -t rms-backend/vehicles-service .