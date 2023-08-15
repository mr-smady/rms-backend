#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/graphql-service
docker build -t rms-backend/graphql-service .