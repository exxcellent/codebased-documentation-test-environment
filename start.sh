#!/usr/bin/env bash

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/env.conf

# stop execution, if parameter list is empty
if [ $# -eq 0 ]
    then {
        echo "No test environment defined. Stopping execution. Use $ sh ./start.sh [bogenliga|bmw|daimler] to start an environment."
        exit 1
        }
    else SERVICES=$@
fi

echo "Starting test environment..."

SCRIPTS=$TEST_ENVIRONMENT_HOME/scripts

# Pull (and clone) git repositories
. $SCRIPTS/pull_all_repos.sh $SERVICES

# Clean build the services
. $SCRIPTS/build_all.sh $SERVICES

# Copy deployment artifacts to the docker staging directories
. $SCRIPTS/deploy_all.sh $SERVICES

# Rebuild docker container
. $SCRIPTS/rebuild_docker.sh $SERVICES

# Migrate database schemas
. $SCRIPTS/run_all_flyways.sh $SERVICES