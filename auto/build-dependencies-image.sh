#!/bin/sh

. $(dirname $0)/config.sh
docker build --no-cache -t "$DEPENDENCIES_TAG" -f auto/Dockerfile.dependencies .
