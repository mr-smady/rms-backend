#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--trips-service \
         rms-backend/trips-service
docker network connect rms-network rms-backend--trips-service