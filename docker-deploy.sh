#! /usr/bin/env bash

docker stop myfda
docker rm -f myfda
docker pull nicfederal/myfda:latest
docker run --name myfda -d -p 443:8443 -v /home/ubuntu/config:/opt/config nicfederal/myfda

