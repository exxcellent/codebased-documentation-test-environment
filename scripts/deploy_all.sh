#!/bin/bash
# Deploys all given jar microservices and restarts the container.
# If the parameter list is empty, all known microservices will be used.
# Parameters: microservice ID of the projects

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf
printf '\nDeploying projects... \n'

DEPLOY_SERVICES=$@

BOGENLIGA_DIR=$TEST_ENVIRONMENT_HOME/bogenliga
BMW_DIR=$TEST_ENVIRONMENT_HOME/bmw-example

for service in $DEPLOY_SERVICES
do
    case $service in
        $BOGENLIGA_ALL_MICROSERVICES)
            NEEDED_SERVICES="$NEEDED_SERVICES bogenliga-frontend"
            NEEDED_SERVICES="$NEEDED_SERVICES bogenliga-backend"
            ;;

        # New "all" microservice label? Add your line here!
    esac

    case $service in
        $BMW_ALL_MICROSERVICES)
            NEEDED_SERVICES="$NEEDED_SERVICES rve"
            ;;

        # New "all" microservice label? Add your line here!
    esac
done

for service in $NEEDED_SERVICES
do 
    case $service in 
        $BOGENLIGA_FRONTEND)
            printf '\nDeploying bogenliga-frontend \n'
            rm -r $BOGENLIGA_DIR/container/bogenliga-frontend/staging/web/*
            cp -r $BOGENLIGA_DIR/project/swt2-bsa-frontend/bogenliga/dist/bogenliga/* $BOGENLIGA_DIR/container/bogenliga-frontend/staging/web
            ;;
        $BOGENLIGA_BACKEND)
            printf '\nDeploying bogenliga-backend \n'
            cp $BOGENLIGA_DIR/project/swt2-bsa-backend/bogenliga/bogenliga-application/target/bogenliga-application-1.0.0.jar $BOGENLIGA_DIR/container/bogenliga-backend/staging/backend.jar
            ;;
        $BMW_RSA_VEHICLE_SERVICES)
            printf '\nDeploying vehicle-services\n'
            cp $BMW_DIR/project/rsa-vehicle-services/rsa-vehicle-services/target/rsa-vehicle-backend-services-0.11.6-SNAPSHOT.war $BMW_DIR/container/rsa-vehicle-services/staging/vehicle-services.war
            # TODO cp $BMW_DIR/project/rsa-vehicle-services/rsa-vehicle-services/target/rsa-vehicle-backend-services-0.11.6-SNAPSHOT.war $BMW_DIR/container/autodeploy-rsa-vehicle-services/rsa-vehicle-backend-services-0.11.6-SNAPSHOT.war
            ;;
        # New plain-jar microservice? Add your line here!
    esac
done 








