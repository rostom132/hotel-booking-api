#!/bin/bash

cd ./api && mvn clean package -DskipTests=true && mv ./target/api-0.0.1-SNAPSHOT.jar ../build/