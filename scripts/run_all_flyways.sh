#!/bin/bash
# Executes flyway migrate for all given projects
# If the parameter list is empty, all known microservices will be used.
# Parameters: microservice ID of the projects

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf

DB_SERVICES=$@
printf '\nExecuting flyway...\n'

# config switches
FLYWAY_CLEAN=false;

# search for config parameters
while [ $# -gt 0 ]
do 
    case "$1" in 
        --clean) FLYWAY_CLEAN=true;;
        *) break;; #terminate while loop since there is no known config parameter anymore
    esac
    shift
done
# all config parameters are processed, remaining parameters are all microservice IDs

if [ "$FLYWAY_CLEAN" = true ] 
    then
        FLYWAY_COMMAND="flyway:clean flyway:migrate"
    else
        FLYWAY_COMMAND="flyway:migrate"
fi


for service in $DB_SERVICES
do 
    case $service in 
        $BOGENLIGA_ALL_MICROSERVICES)
            printf '\nExecuting flyway for bogenliga \n'
            mvn -f $TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-backend/bogenliga/bogenliga-db-migration/pom.xml $FLYWAY_COMMAND -PLOCAL
            ;;
        # New microservice? Add your line here!
    esac
done 
