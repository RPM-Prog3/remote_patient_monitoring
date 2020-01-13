#!/bin/bash
cd ..
>db_config.properties
echo "db_name=travis_test_db" >> db_config.properties
echo "db_user_name=postgres" >> db_config.properties
echo "db_password=admin" >> db_config.properties
>server_config.properties
