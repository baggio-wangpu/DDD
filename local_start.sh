#!/usr/bin/env bash
docker-compose up -d
sleep 10s
./gradlew bootRun

