#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--zones-service \
         rms-backend/zones-service
docker network connect rms-network rms-backend--zones-service