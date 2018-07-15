FROM alpine:3.7

MAINTAINER Marcello Rego <marcellorego@gmail.com>
LABEL Description="Mosquitto MQTT Broker Server"

ARG MOSQUITTO_BUILD=1.4.15-r0
ARG DEFAULT_USER=admin
ARG DEFAULT_USER_PASSWORD=admin123

RUN apk --no-cache add mosquitto=$MOSQUITTO_BUILD && \
    mkdir -p /mqtt/config /mqtt/config/auth /mqtt/data /mqtt/log && \
    touch /mqtt/config/auth/pwfile && \
    mosquitto_passwd -b /mqtt/config/auth/pwfile $DEFAULT_USER $DEFAULT_USER_PASSWORD

COPY config /mqtt/config/
COPY entrypoint.d/docker-entrypoint.sh /mqtt/entrypoint.d/

RUN chown -R mosquitto:mosquitto /mqtt && \
    chmod +x /mqtt/entrypoint.d/docker-entrypoint.sh

VOLUME ["/mqtt/config", "/mqtt/data", "/mqtt/log"]
EXPOSE 1883 9001

ENTRYPOINT ["/mqtt/entrypoint.d/docker-entrypoint.sh"]
CMD ["/usr/sbin/mosquitto", "-c", "/mqtt/config/mosquitto.conf"]