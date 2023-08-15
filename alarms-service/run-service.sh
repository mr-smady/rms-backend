#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--alarms-service \
         rms-backend/alarms-service
docker network connect rms-network rms-backend--alarms-service