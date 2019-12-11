#!/bin/bash
set -ev
echo "Running for application"
cd $PWD
cd Application/
sudo apt update
sudo apt install openjfx
chmod +x gradlew
./gradlew build --info