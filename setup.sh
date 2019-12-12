#!/bin/bash
echo "Welcome to the remote patient monitoring system - we are just setting a few things up."
echo "--------------------------------------------------------------------------------------"
echo "1. Adding config.properties file, please add your database details here."
cd Server
touch config.properties
echo "db_name=YOUR_DATABASE_NAME_HERE" >> config.properties
echo "db_user_name=YOUR_USER_NAME_HERE" >> config.properties
echo "db_password=YOUR_PASSWORD_HERE" >> config.properties
echo "1. Done"