#!/bin/bash
./stop-service.sh
docker image rm -f rms-backend/companies-service
docker build -t rms-backend/companies-service .