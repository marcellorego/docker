SHELL := /usr/bin/env bash
.DEFAULT_GOAL := help
.PHONY: run

run:
	docker run -d -p 9000:9000 \
	-v /private/var/run/docker.sock:/var/run/docker.sock \
	-v portainer_data:/data \
	portainer/portainer