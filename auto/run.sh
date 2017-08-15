#!/bin/sh

. $(dirname $0)/config.sh

docker pull ${APP_TAG}:${APP_VERSION}
docker run -i ${APP_TAG}:${APP_VERSION}
