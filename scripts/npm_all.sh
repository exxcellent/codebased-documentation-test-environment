#!/bin/bash
# Executes an arbitrary maven command for all given projects.
# If the parameter list is empty, all known microservices will be used.
# Parameters: [--command <mvn-command>] [microserviceIDs ...]
#   --command : the given command will be used for maven, default: clean package
#   microservice ID of the projects

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/../env.conf

NODE_SERVICES=$@
printf '\nBuilding nodejs projects...\n'

# configs
NPM_COMMAND="install"


# search for config parameters
while [ $# -gt 0 ]
do 
    case "$1" in 
        --command)
            # save the next parameter as maven command
            shift
            NPM_COMMAND=$1
            ;;
        *) break;; #terminate while loop since there is no known config parameter anymore
    esac
    shift
done
# all config parameters are processed, remaining parameters are all microservice IDs


for service in $NODE_SERVICES
do
    case $service in
        $BOGENLIGA_FRONTEND)
            printf "\nExecuting command: %s \n" "npm $NPM_COMMAND"
            cd  $TEST_ENVIRONMENT_HOME/bogenliga/project/swt2-bsa-frontend/bogenliga
            npm $NPM_COMMAND
            ;;

        # New microservice? Add your line here!
    esac
done 
