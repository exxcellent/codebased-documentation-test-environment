#!/bin/bash
# Removes all docker containers. Builds and starts all containers for the given microservices and defaults like s3 or db.
# If the parameter list is empty, all known microservices will be used.
# Parameters: [--keep] [microserviceIDs ...]
#   --keep : docker-compose will not stop and remove all containers
#   microservice ID of the projects

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf
printf '\nRebuilding docker containers...\n'

cd $TEST_ENVIRONMENT_HOME

# config switches
KEEP_CONTAINERS=false;

SERVICES=$@

echo "SERVICES: $SERVICES"

for service in $SERVICES
do 
    case $service in 
        $BOGENLIGA_ALL_MICROSERVICES)
            COMPOSE_FILE=$TEST_ENVIRONMENT_HOME/bogenliga/docker-compose.yml
            NEEDED_SERVICES="$NEEDED_SERVICES bogenliga-frontend"
            NEEDED_SERVICES="$NEEDED_SERVICES bogenliga-backend"
            NEEDED_SERVICES="$NEEDED_SERVICES bogenliga-db"
            ;;

        $BMW_ALL_MICROSERVICES)
            COMPOSE_FILE=$TEST_ENVIRONMENT_HOME/bmw-example/docker-compose.yml
            NEEDED_SERVICES="$NEEDED_SERVICES rve"
            #NEEDED_SERVICES="$NEEDED_SERVICES bmw-db"
            ;;
        # New microservice? Add your line here!
    esac
done 


echo "Stopping and removing all containers"
echo "docker-compose -f $COMPOSE_FILE down"
docker-compose -f $COMPOSE_FILE down

echo "docker-compose -f $COMPOSE_FILE build $NEEDED_SERVICES"
#always try to rebuild images, to never miss an update!
docker-compose -f $COMPOSE_FILE build $NEEDED_SERVICES

echo "docker-compose -f $COMPOSE_FILE up -d $NEEDED_SERVICES"
#and start containers
docker-compose -f $COMPOSE_FILE up -d $NEEDED_SERVICES