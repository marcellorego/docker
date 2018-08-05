FROM alpine:3.7

LABEL Author="Marcello Rego <marcellorego@gmail.com>"
LABEL Description="Mosquitto MQTT Test Client SUB"

ARG MOSQUITTO_BUILD=1.4.15-r0
ARG DEFAULT_USER=admin
ARG DEFAULT_USER_PASSWORD=admin123
ARG DEFAULT_TOPIC=temperature

RUN apk --no-cache add mosquitto-clients=$MOSQUITTO_BUILD

COPY script.sh sub/

RUN chmod +x sub/script.sh

ENTRYPOINT ["/sub/script.sh"]