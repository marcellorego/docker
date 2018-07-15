docker network create --driver bridge 4mart-mqtt-net
docker network rm 4mart-mqtt-net
docker network inspect 4mart-mqtt-net
--network 4mart-mqtt-net

mosquitto_pub -h mqtt_server -u admin -P admin123 -d -t hello/world -m "Hello from Terminal window 2!"
mosquitto_sub -h mqtt_server -u admin -P admin123 -d -t hello/world