#!/bin/bash
./stop-service.sh
docker run -d --name rms-backend--companies-service \
         rms-backend/companies-service
docker network connect rms-network rms-backend--companies-service