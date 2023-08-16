#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--locations-service \
         rms-backend/locations-service
docker network connect rms-network rms-backend--locations-service