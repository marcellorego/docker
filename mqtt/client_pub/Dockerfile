FROM alpine:3.7

MAINTAINER Marcello Rego <marcellorego@gmail.com>
LABEL Description="Mosquitto MQTT Test Client PUB"

ARG MOSQUITTO_BUILD=1.4.15-r0
ARG DEFAULT_USER=admin
ARG DEFAULT_USER_PASSWORD=admin123
ARG DEFAULT_TOPIC=hello/world

RUN apk --no-cache add mosquitto-clients=$MOSQUITTO_BUILD

CMD ["/usr/bin/mosquitto_pub", "-h", "mqtt_server", "-u", "admin", "-P", "admin123", "-d", "-t", "hello/world", "-m", "Publishing message 'Hello World'..."]