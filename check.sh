#!/bin/sh

#!/usr/bin/env bash

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/env.conf
SCRIPTS=$TEST_ENVIRONMENT_HOME/scripts

# All necessary tools have to be installed

. $SCRIPTS/check-tools.sh git "Git" https://git-scm.com/
. $SCRIPTS/check-tools.sh mvn "Apache Maven" https://maven.apache.org/
. $SCRIPTS/check-tools.sh node "Node.js" https://nodejs.org/
. $SCRIPTS/check-tools.sh npm "Node Package Manager (NPM)" https://www.npmjs.com/get-npm
. $SCRIPTS/check-tools.sh docker "Docker" https://www.docker.com/community-edition
. $SCRIPTS/check-tools.sh docker-compose "Docker-Compose" https://docs.docker.com/compose/install/

