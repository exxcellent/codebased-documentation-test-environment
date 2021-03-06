#!/bin/bash

# $1 := command
# $2 := name
# $3 := web resource

"$1" --version 2>&1 >/dev/null
IS_AVAILABLE=$?

if [ $IS_AVAILABLE -eq 0 ]; then 
echo "$1 [OK]";
else
echo "(!) $2 not found. Please install $2 from $3 and try it again.";
exit 0;
fi
