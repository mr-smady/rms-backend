#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--vehicles-service \
         rms-backend/vehicles-service
docker network connect rms-network rms-backend--vehicles-service