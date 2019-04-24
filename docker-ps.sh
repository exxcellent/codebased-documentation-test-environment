#!/usr/bin/env bash

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/env.conf

# stop execution, if parameter list is empty
if [ $# -eq 0 ]
    then {
        echo "No test environment defined. Stopping execution. Use $ sh ./docker-ps.sh [bogenliga|bmw|daimler]."
        exit 1
        }
    else SERVICES=$@
fi

for service in $SERVICES
do
    case $service in
        $BOGENLIGA_ALL_MICROSERVICES)
            COMPOSE_FILE=$TEST_ENVIRONMENT_HOME/bogenliga/docker-compose.yml
            ;;

        # New microservice? Add your line here!
    esac
done

echo "docker-compose -f $COMPOSE_FILE ps"
docker-compose -f $COMPOSE_FILE ps
