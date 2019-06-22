#!/bin/bash
# Builds all given projects via maven package without executing the tests.
# If the parameter list is empty, all known microservices will be used.
# Parameters: microservice ID of the projects 

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf
printf '\nBuilding projects... \n'

for service in $SERVICES
do
    case $service in
        $BOGENLIGA_ALL_MICROSERVICES)
            NPM_SERVICES="$NPM_SERVICES bogenliga-frontend"
            MAVEN_SERVICES="$MAVEN_SERVICES bogenliga-backend"
            ;;
        $BMW_ALL_MICROSERVICES)
            MAVEN_SERVICES="$MAVEN_SERVICES rve"
            ;;
        # New microservice? Add your line here!
    esac
done

. $TEST_ENVIRONMENT_HOME/scripts/maven_all.sh --command "-Dmaven.test.skip=true package" $MAVEN_SERVICES

# install dependencies (double install to fix known ngrx-store dependency bug)
. $TEST_ENVIRONMENT_HOME/scripts/npm_all.sh $NPM_SERVICES
. $TEST_ENVIRONMENT_HOME/scripts/npm_all.sh $NPM_SERVICES

# build nodejs projects
. $TEST_ENVIRONMENT_HOME/scripts/npm_all.sh --command "run build:prod" $NPM_SERVICES
