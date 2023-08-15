#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--manifest-service \
         rms-backend/manifest-service
docker network connect rms-network rms-backend--manifest-service