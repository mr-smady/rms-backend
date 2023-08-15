#!/bin/bash
sudo docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=P@ssw0rd" \
   -p 1433:1433 --name rms-ms-sql-server --hostname rms-ms-sql-server \
   -d \
   mcr.microsoft.com/mssql/server:2022-latest


sudo docker run --name ubuntu ubuntu:latest

docker network create myNetwork

docker network connect rms-network  confident_ritchie
docker exec -it  awesome_edison /bin/bash

docker-compose down
docker rm -f $(docker ps -a -q)
docker volume rm $(docker volume ls -q)
docker-compose up -d


docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)


docker ps --filter status=exited -q | xargs docker rm


docker container rm ubuntu
docker run --name ubuntu -it ubuntu /bin/bash

docker network connect rms-network ubuntu


apt-get update -y
apt-get install -y iputils-ping
apt-get install xinetd telnetd

apt update
apt install telnet

ping rms-backend--graphql-service