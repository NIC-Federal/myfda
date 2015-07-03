FROM maven
RUN apt-get update && apt-get install bzip2 sudo
ADD foorbar/ /tmp/
ADD target/myfda.jar /opt/myfda
WORKDIR /opt/myfda
VOLUME /opt/config
CMD java -jar /opt/myfda/target/myfda.jar --spring.config.location=file:/opt/config/application.properties
