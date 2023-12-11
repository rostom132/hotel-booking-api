#!/bin/bash

docker-compose up -d --build

echo "Seeding Elastic Search data ..."
cd es/ && ./elasticsearch-init.sh