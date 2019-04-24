#!/bin/bash
# Executes an arbitrary maven command for all given projects.
# If the parameter list is empty, all known microservices will be used.
# Parameters: [--command <mvn-command>] [microserviceIDs ...]
#   --command : the given command will be used for maven, default: clean package
#   microservice ID of the projects

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf
MVN_SERVICES=$@
printf '\nBuilding maven projects...\n'

# configs
MAVEN_COMMAND="clean package"

# search for config parameters
while [ $# -gt 0 ]
do 
    case "$1" in 
        --command)
            # save the next parameter as maven command
            shift
            MAVEN_COMMAND=$1
            ;;
        *) break;; #terminate while loop since there is no known config parameter anymore
    esac
    shift
done
# all config parameters are processed, remaining parameters are all microservice IDs



for service in $MVN_SERVICES
do 
    case $service in 
        $BOGENLIGA_BACKEND)
            printf "\nExecuting command: %s \n" "mvn -f $TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-backend/bogenliga/pom.xml $MAVEN_COMMAND"
            mvn -f $TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-backend/bogenliga/pom.xml $MAVEN_COMMAND
            ;;

        # New microservice? Add your line here!
    esac
done 
