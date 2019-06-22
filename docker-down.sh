#!/usr/bin/env bash

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/env.conf

# stop execution, if parameter list is empty
if [ $# -eq 0 ]
    then {
        echo "No test environment defined. Stopping execution. Use $ sh ./docker-ps.sh [all|bogenliga|bmw|daimler]."
        exit 1
        }
    else SERVICE=$@
fi

if [ "$SERVICE" == "$BMW" ]; then
    COMPOSE_FILE=$BMW_COMPOSE_FILE
    echo "docker-compose -f $COMPOSE_FILE down"
    docker-compose -f $COMPOSE_FILE down
elif [ "$SERVICE" == "$BOGENLIGA" ]; then
    COMPOSE_FILE=$BOGENLIGA_COMPOSE_FILE
    echo "docker-compose -f $COMPOSE_FILE down"
    docker-compose -f $COMPOSE_FILE down
elif [ "$SERVICE" == "all" ]; then
    echo "Stopping all test environments"

    COMPOSE_FILE=$BOGENLIGA_COMPOSE_FILE
    echo "docker-compose -f $COMPOSE_FILE down"
    docker-compose -f $COMPOSE_FILE down

    COMPOSE_FILE=$BMW_COMPOSE_FILE
    echo "docker-compose -f $COMPOSE_FILE down"
    docker-compose -f $COMPOSE_FILE down
fi


