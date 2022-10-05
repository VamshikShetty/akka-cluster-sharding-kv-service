#!/bin/bash

CURRENT_UID=$(id -u)
CURRENT_GID=$(id -g)

OPENAPI_SPEC_FILE=spec/kv-store-akka-sharding.yaml
OPENAPI_CONFIG=spec/oas3.config.json
OPENAPI_GENERATED_SERVER=akka-sharded-kv

OPENAPI_CODEGEN_TAG=latest
OPENAPI_CODEGEN_IMAGE=openapitools/openapi-generator-cli:$OPENAPI_CODEGEN_TAG

docker run --rm \
	-u $CURRENT_UID:$CURRENT_GID \
	-v $(pwd):/local $OPENAPI_CODEGEN_IMAGE \
	generate \
	-i /local/$OPENAPI_SPEC_FILE \
	-g spring \
	-c /local/$OPENAPI_CONFIG \
	-o /local/$OPENAPI_GENERATED_SERVER