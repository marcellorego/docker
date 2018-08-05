docker network create --driver bridge 4mart-mqtt-net
docker network rm 4mart-mqtt-net
docker network inspect 4mart-mqtt-net
--network 4mart-mqtt-net

mosquitto_pub -h mqtt_server -u admin -P admin123 -d -t temperature -m "\{\temp\":\"23\}"
mosquitto_sub -h mqtt_server -u admin -P admin123 -d -t temperature

mvn spring-boot:run