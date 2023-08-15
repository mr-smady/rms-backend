#!/bin/bash
./stop-service.sh
docker run -d -p 80:8080 \
    --name rms-backend--graphql-service \
    rms-backend/graphql-service
docker network connect rms-network rms-backend--graphql-service