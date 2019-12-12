#!/bin/bash
echo "Welcome to the remote patient monitoring system"
echo "-----------------------------------------------"
echo "Setting up..."
echo "-----------------------------------------------"
echo "1. Adding db_config.properties file, please add database details here."
cd Server
touch db_config.properties
echo "db_name=YOUR_DATABASE_NAME_HERE" >> db_config.properties
echo "db_user_name=YOUR_USER_NAME_HERE" >> db_config.properties
echo "db_password=YOUR_PASSWORD_HERE" >> db_config.properties
echo "1. Done"

echo "2. Adding server_config.properties file, please add server details here."
cd Server
touch server_config.properties
echo "server_ip=YOUR_SERVER_IP_HERE" >> server_config.properties
echo "2. Done"