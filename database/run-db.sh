#!/bin/bash
docker network create rms-network
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=P@ssw0rd" \
    -p 1433:1433 --name rms-ms-sql-server -d \
    mcr.microsoft.com/mssql/server:2022-latest

docker network connect rms-network rms-ms-sql-server