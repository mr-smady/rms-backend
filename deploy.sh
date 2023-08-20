#!/bin/bash

git pull
cd ~/rms-backend || goto exit_error
chmod +x gradlew
 ./gradlew clean build


cd ~/rms-backend/alarms-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend/companies-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend/graphql-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend/manifest-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend/trips-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend/vehicles-service || goto exit_error
chmod +x ./*.sh
./build-image.sh
./run-service.sh
cd ~/rms-backend || goto exit_error
exit
exit_error:
echo 'ERROR'
exit
