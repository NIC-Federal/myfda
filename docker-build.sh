#!/bin/bash
docker build -t nicfederal/myfda .
if [ $? -ne 0 ] 
then
	exit $?;
fi
docker push nicfederal/myfda
