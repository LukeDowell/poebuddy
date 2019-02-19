#!/usr/bin/env bash

docker pull postgres
docker run --name poebuddy-postgres -e POSTGRES_PASSWORD=devpassword -d postgres