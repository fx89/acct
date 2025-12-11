#!/bin/bash

# Enable Docker CLI experimental features to be able to download newer image versions
export DOCKER_CLI_EXPERIMENTAL=enabled

# Create the data directories for the databases
mkdir data
mkdir data/security
mkdir data/user_management
mkdir data/jobs
mkdir data/workspace
mkdir data/catalog
mkdir data/reporting

# Start the services
docker compose \
  --env-file _environment.txt \
  --project-name acct \
  --profile everything \
  up -d
