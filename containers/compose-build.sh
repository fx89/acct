#!/bin/bash

# Start the services
docker-compose \
  --env-file _environment.txt \
  --project-name acct \
  --profile everything \
  build