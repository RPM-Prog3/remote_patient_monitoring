#!/bin/bash
set -ev
echo "Running for server"
cd $PWD
cd Server/
sudo apt update
chmod +x gradlew
./gradlew build --info