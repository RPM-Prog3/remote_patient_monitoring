#!/bin/bash
echo "Welcome to the remote patient monitoring system"
echo "-----------------------------------------------"
echo "Setting up..."
echo "-----------------------------------------------"

echo "Adding server_config.properties file, please add server details here."

cd Server
>server_config.properties

read -p "Is this computer hosting the server or a client (regular app)? [Write y for hosting]:" hosting

if [ "${hosting}" = y ]
then
  ip="localhost"
else
  read -p "Do you want to set the server IP now? [Write y to set IP now]: " set_ip_now

  if [ "${set_ip_now}" = "y" ]
  then
    read -p "Enter the IP of the server: " ip
  else
    ip="SERVER_IP_HERE"
  fi
fi

echo "server_ip=${ip}" >> server_config.properties
echo "Server config setup done."

if [ "${hosting}" = y ]
then
  echo "-----------------------------------------------"
	echo "Adding db_config.properties file, please add the PostgreSQL details here."
	>db_config.properties

	read -p "Do you want to setup the users now? [Write y to setup now]: " setup_db_now

	if [ "${setup_db_now}" = "y" ]
	then
		read -p "Do you want to use default values? [Write y to use default]: " default_values 
		if [ "$default_values" = "y" ]
		then
			db_user_name="postgres"
			db_password="admin"
    else
      read -p "Enter your database user name: " db_user_name
      read -p "Enter your database password: " db_password
    fi
  else
    db_user_name="YOUR_USER_NAME_HERE"
    db_password="YOUR_PASSWORD_HERE"
  fi

  echo "-----------------------------------------------"
  echo "Creating the database."

  if [ "$setup_db_now" = "y" ]
  then
    echo "If you already have a database called 'remote_patient_monitoring', then don't use the default name."
    read -p "Do you want to use the default database name? [Write y to use]: " default_name

    if [ "$default_name" = "y" ]
    then
      db_user_name="remote_patient_monitoring"

    else
      read -p "Enter the custom database name: " db_name
    fi
  else
    db_name="YOUR_DATABASE_NAME_HERE"
  fi

  echo "-----------------------------------------------"
  echo "Writing to db_config.properties"

  # shellcheck disable=SC2129
  echo "db_name=${db_name}" >> db_config.properties
  echo "db_user_name=${db_user_name}" >> db_config.properties
  echo "db_password=${db_password}" >> db_config.properties

  echo "PostgreSQL setup done."
fi
cd ..

echo "Setup complete"

