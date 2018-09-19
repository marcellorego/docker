SHELL := /usr/bin/env bash
.DEFAULT_GOAL := help
.PHONY: rm run

CURRENT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
DB_IMAGE_NAME?=4mart/mongo
DB_IMAGE_TAG?=0.0.1
DB_CONTAINER_NAME?=ps006_db
APPLICATION_DATABASE?=ps006

help:
	@echo ''
	@echo 'Makefile for '
	@echo '     make help				show this information'
	@echo '     make rundbnet			run $(DB_IMAGE_NAME) image, creates container $(DB_CONTAINER_NAME) with network name `4mart-mqtt-net`'
	@echo '     make shdb				run a shell on the container $(DB_CONTAINER_NAME)'
	@echo '     make rmdb				remove container $(DB_CONTAINER_NAME)'
	@echo '     make startdb			start the container $(DB_CONTAINER_NAME)'
	@echo '     make stopdb			stop the container $(DB_CONTAINER_NAME)'

rmdb:
	docker rm -f $(DB_CONTAINER_NAME)

#-v $(CURRENT_DIR)/../data/db:/data/db
#--logpath /var/log/mongodb/mongo.log
rundbnet:
	docker run -d \
		--name $(DB_CONTAINER_NAME) \
		--network 4mart-mqtt-net \
		-p 27017:27017 \
		-e MONGO_INITDB_APPLICATION_DATABASE=$(APPLICATION_DATABASE) \
		$(DB_IMAGE_NAME):$(DB_IMAGE_TAG)

shdb:
	docker exec -it $(DB_CONTAINER_NAME) bash

startdb:
	docker start $(DB_CONTAINER_NAME)

stopdb:
	docker stop $(DB_CONTAINER_NAME)
 