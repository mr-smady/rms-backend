#!/bin/bash
cd ~/services/alarms-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services/companies-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services/graphql-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services/manifest-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services/trips-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services/vehicles-service || goto exit_error
./build-image.sh
./run-service.sh
cd ~/services || goto exit_error
exit
exit_error:
echo 'ERROR'
exit
