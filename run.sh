#!/usr/bin/env bash

#load environment config values
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $DIR/env.conf

# stop execution, if parameter list is empty
if [ $# -eq 0 ]
    then {
        echo "No test environment defined. Stopping execution. Use $ sh ./run.sh [bogenliga|$BMW|daimler] to start an environment."
        exit 1
        }
    else SERVICE=$@
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "Starting '$SERVICE' test environment..."

if [ "$SERVICE" == "$BMW" ]; then

    ### BMW

    BMW_VES=vehicle-services
    BMW_VEI=vehicle-information
    BMW_VEM=vehicle-management

    BMW_VES_WAR=vehicle-services-application-1.0.0-SNAPSHOT.war
    BMW_VEI_WAR=vehicle-information-application-1.0.0-SNAPSHOT.war
    BMW_VEM_WAR=vehicle-management-application-1.0.0-SNAPSHOT.war


    printf "\nBuild Maven: %s \n" "mvn -f $BMW_PROJECT_DIR/$BMW_VES/root-pom/pom.xml clean package"
    mvn -f $BMW_PROJECT_DIR/$BMW_VES/root-pom/pom.xml clean package
    printf "\nBuild Maven: %s \n" "mvn -f $BMW_PROJECT_DIR/$BMW_VEI/root-pom/pom.xml clean package"
    mvn -f $BMW_PROJECT_DIR/$BMW_VEI/root-pom/pom.xml clean package
    printf "\nBuild Maven: %s \n" "mvn -f $BMW_PROJECT_DIR/$BMW_VEM/root-pom/pom.xml clean package"
    mvn -f $BMW_PROJECT_DIR/$BMW_VEM/root-pom/pom.xml clean package

    printf '\nCopy sources to staging folders\n'
    cp $BMW_PROJECT_DIR/$BMW_VES/vehicle-services-application/target/$BMW_VES_WAR $BMW_DOCKER_DIR/$BMW_VES/staging/$BMW_VES.war
    cp $BMW_PROJECT_DIR/$BMW_VEI/vehicle-information-application/target/$BMW_VEI_WAR $BMW_DOCKER_DIR/$BMW_VEI/staging/vehicle-information.war
    cp $BMW_PROJECT_DIR/$BMW_VEM/vehicle-management-application/target/$BMW_VEM_WAR $BMW_DOCKER_DIR/$BMW_VEM/staging/vehicle-management.war

    printf '\nStopping and removing all services\n'
    docker-compose -f $BMW_COMPOSE_FILE down
    docker-compose -f $BOGENLIGA_COMPOSE_FILE down

    echo "docker-compose -f $BMW_COMPOSE_FILE up --build -d $BMW_VES $BMW_VEI $BMW_VEM"
    #and start containers
    docker-compose -f $BMW_COMPOSE_FILE up --build -d $BMW_VES $BMW_VEI $BMW_VEM

    echo "The application server is starting. Please wait some seconds..."


elif [ "$SERVICE" == "$BOGENLIGA" ]; then

    ### Bogenliga

    BOGENLIGA_FRONTEND_REPO=https://github.com/exxcellent/swt2-bsa-frontend.git
    BOGENLIGA_BACKEND_REPO=https://github.com/exxcellent/swt2-bsa-backend.git
    BOGENLIGA_FRONTEND_PROJECT=$BOGENLIGA_PROJECT_DIR/swt2-bsa-frontend
    BOGENLIGA_BACKEND_PROJECT=$BOGENLIGA_PROJECT_DIR/swt2-bsa-backend

    BOGENLIGA_FRONTEND=bogenliga-frontend
    BOGENLIGA_BACKEND=bogenliga-backend
    BOGENLIGA_DB=bogenliga-db

    BOGENLIGA_BACKEND_JAR=bogenliga-application-1.0.0.jar

    if [ ! -d "$BOGENLIGA_FRONTEND_PROJECT" ]; then
      git clone $BOGENLIGA_FRONTEND_REPO $BOGENLIGA_FRONTEND_PROJECT
    else
      git -C $BOGENLIGA_FRONTEND_PROJECT fetch --all
      git -C $BOGENLIGA_FRONTEND_PROJECT reset --hard origin/master
    fi
    if [ ! -d "$BOGENLIGA_BACKEND_PROJECT" ]; then
      git clone $BOGENLIGA_BACKEND_REPO $BOGENLIGA_BACKEND_PROJECT
    else
      git -C $BOGENLIGA_BACKEND_PROJECT fetch --all
      git -C $BOGENLIGA_BACKEND_PROJECT reset --hard origin/master
    fi

    printf "\nBuild Maven: %s \n" "mvn -f $BOGENLIGA_BACKEND_PROJECT/bogenliga/pom.xml clean package"
    mvn -f $BOGENLIGA_BACKEND_PROJECT/bogenliga/pom.xml clean package

    printf "\nBuild Angular: %s \n" "cd $BOGENLIGA_FRONTEND_PROJECT/bogenliga && npm run build:prod"
    cd  $BOGENLIGA_FRONTEND_PROJECT/bogenliga
    npm install
    npm install
    npm run build:prod
    printf "\nRun: %s \n" "cd $DIR"
    cd $DIR

    printf '\nCopy sources to staging folders\n'
    cp $BOGENLIGA_BACKEND_PROJECT/bogenliga/bogenliga-application/target/$BOGENLIGA_BACKEND_JAR $BOGENLIGA_DOCKER_DIR/bogenliga-backend/staging/$BOGENLIGA_BACKEND.jar

    rm -rf $BOGENLIGA_DOCKER_DIR/bogenliga-frontend/staging/web/*
    cp -r $BOGENLIGA_FRONTEND_PROJECT/bogenliga/dist/bogenliga/* $BOGENLIGA_DOCKER_DIR/bogenliga-frontend/staging/web

    printf '\nStopping and removing all services\n'
    docker-compose -f $BMW_COMPOSE_FILE down
    docker-compose -f $BOGENLIGA_COMPOSE_FILE down

    echo "docker-compose -f $BOGENLIGA_COMPOSE_FILE up --build -d $BOGENLIGA_DB $BOGENLIGA_FRONTEND $BOGENLIGA_BACKEND"
    #and start containers
    docker-compose -f $BOGENLIGA_COMPOSE_FILE up --build -d $BOGENLIGA_DB $BOGENLIGA_FRONTEND $BOGENLIGA_BACKEND

    echo "Waiting..."
    sleep 10

    printf '\nExecuting flyway for bogenliga \n'
    mvn -f $BOGENLIGA_BACKEND_PROJECT/bogenliga/bogenliga-db-migration/pom.xml flyway:clean flyway:migrate -PLOCAL

    echo "The bogenliga is starting. Please wait some seconds... and open http://localhost"

else
   echo "Unknown parameter"
fi

