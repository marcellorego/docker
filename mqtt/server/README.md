#Eclipse Mosquitto v1.4.15 Docker Image

##Commands
All docker commands are made through the Makefile. 
So in order to display a help, just do **make help** at the same folder of Makefile file, and all common commands will be explained.

##Mount Points

Three mount points have been created in the image to be used for configuration, persistent storage and logs.
```
/mqtt/config
/mqtt/data
/mqtt/log
```

##Configuration

When running the image, the default configuration values are used.
To use a custom configuration file, change the **local** configuration file at `/mqtt/config/mosquitto.conf`

Configuration can be changed to:

* persist data to `/mqtt/data/`
* log to `/mqtt/log/mosquitto.log`

i.e. add the following to `mosquitto.conf`:
```
persistence true
persistence_location /mqtt/data/

log_dest file /mqtt/log/mqtt.log
```

**Note**: If a volume is used, the data will persist between containers.

##Build
Build the image:
```
make build 
```

Available arguments and default values for image build:
IMAGE_NAME=4mart/mosquitto
IMAGE_TAG=0.0.1

To change it, just pass as argument, the desired parameter.

```
make build **image_name**:**tag**
```

##Run
Run a container using the new image:
```
make run
```
Available argument and default value name for container creation:
CONTAINER_NAME=mqtt

To change it, just pass as argument, the desired parameter.

```
make run **container_name**
```

:boom: if the mosquitto configuration (mosquitto.conf) was modified
to use non-default ports, the docker run command will need to be updated
to expose the ports that have been configured.