SHELL := /usr/bin/env bash
.DEFAULT_GOAL := help
.PHONY: rm run

CURRENT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
IMAGE_NAME?=4mart/mosquitto_client_pub
IMAGE_TAG?=0.0.1
CONTAINER_NAME?=mqtt_client_pub

help:
	@echo ''
	@echo 'Makefile for '
	@echo '     make help		show this information'
	@echo '     make build		build $(IMAGE_NAME) image'
	@echo '     make run		run $(IMAGE_NAME) image and creates container $(CONTAINER_NAME)'
	@echo '     make sh		run a shell on the container $(CONTAINER_NAME)'
	@echo '     make rm		remove container $(CONTAINER_NAME)'

build:
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .

rm:
	docker rm -f $(CONTAINER_NAME)

run:
	docker run -itd --name $(CONTAINER_NAME) $(IMAGE_NAME):$(IMAGE_TAG)

net:
	docker run -itd --name $(CONTAINER_NAME) --network 4mart-mqtt-net $(IMAGE_NAME):$(IMAGE_TAG)

netsh:
	docker run -it --name $(CONTAINER_NAME) --network 4mart-mqtt-net $(IMAGE_NAME):$(IMAGE_TAG) sh

sh:
	docker exec -it $(CONTAINER_NAME) sh

start:
	docker start $(CONTAINER_NAME)

stop:
	docker stop $(CONTAINER_NAME)
 