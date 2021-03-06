#!/usr/bin/env bash
set -e

# http://graysonkoonce.com/managing-docker-and-docker-compose-versions-on-travis-ci/

DOCKER_VERSION=1.12.0-0~trusty
DOCKER_COMPOSE_VERSION=1.8.0

# list docker-engine versions
apt-cache madison docker-engine

# upgrade docker-engine to specific version
sudo apt-get -o Dpkg::Options::="--force-confnew" install -y --force-yes docker-engine=${DOCKER_VERSION}

# reinstall docker-compose at specific version
sudo rm -f /usr/local/bin/docker-compose
curl -L https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-`uname -s`-`uname -m` > docker-compose
chmod +x docker-compose
sudo mv docker-compose /usr/local/bin

docker version
docker-compose version