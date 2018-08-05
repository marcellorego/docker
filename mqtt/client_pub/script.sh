#!/bin/sh

COUNT=1
while [ $COUNT -le 10 ]
do
  TEMP=$(( ( RANDOM % 100 )  + 1 ))
	echo "Temperature is $TEMP"
  /usr/bin/mosquitto_pub -h mqtt_server -u admin -P admin123 -d -t temperature -m "{value: $TEMP}"
	COUNT=$((COUNT+1))
  sleep 5
done