#!/bin/sh

set -e
#[ -f /mqtt/log/mqtt.log ] && mv /mqtt/log/mqtt.log /mqtt/log/$(date +%F-%H:%M).log
exec "$@"