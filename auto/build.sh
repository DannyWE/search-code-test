#!/bin/sh

. $(dirname $0)/config.sh
docker pull $DEPENDENCIES_TAG
docker build -t dockertry/search-code-test -f auto/Dockerfile.building .
