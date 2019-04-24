#!/bin/bash
# Pulls all git repositories for the given microservices.
# If the parameter list is empty, all known microservices will be used.
# Parameters: microservice ID of the projects 

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf
echo "Pulling git repositories..."

GIT_SERVICES=$@

for service in $GIT_SERVICES
do 
    case $service in 
        $BOGENLIGA_ALL_MICROSERVICES)
            printf '\nPulling bogenliga \n'

            BOGENLIGA_FRONTEND=$TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-frontend/

            if [ ! -d "$BOGENLIGA_FRONTEND" ]; then
              git clone $BOGENLIGA_FRONTEND_REPO $BOGENLIGA_FRONTEND
            else
              git -C $BOGENLIGA_FRONTEND pull
            fi

            BOGENLIGA_BACKEND=$TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-backend/

            if [ ! -d "$BOGENLIGA_BACKEND" ]; then
              git clone $BOGENLIGA_BACKEND_REPO $BOGENLIGA_BACKEND
            else
              git -C $BOGENLIGA_BACKEND pull
            fi
            ;;

        # New microservice? Add your line here!
    esac
done 
