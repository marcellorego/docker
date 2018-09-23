#!/bin/bash

: ${SLEEP_LENGTH:=2}
: ${SLEEP_COUNT:=2}

notReady() {
  nc -vz $1 $2 &> /dev/null
  echo $?
}

wait_for() {
  
  c=1;
  while [ $c -le $SLEEP_COUNT ] && [ $(notReady $1 $2) ]
  do
    echo "$c - waiting for $1 to listen on $2 ..." >&2
    (( c++ ))
    sleep $SLEEP_LENGTH;
  done

  if [ $c -lt $SLEEP_COUNT ] 
  then
    echo 1;
  else
    echo 0;
  fi
}

RESULTS=0;
TOTAL=0;
for var in "$@"
do
  (( TOTAL++ ))
  HOST=${var%:*};
  PORT=${var#*:};
  RESULT=$( wait_for $HOST $PORT );
  RESULTS=$(($RESULTS+$RESULT))
done

if [ $RESULTS == $TOTAL ] 
then
  bash -c 'java -jar app.jar';
fi