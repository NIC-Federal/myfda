FROM maven
RUN apt-get update && apt-get install bzip2 sudo
ADD target/myfda.jar /opt/myfda/myfda.jar
WORKDIR /opt/myfda
VOLUME /opt/config
CMD java -jar /opt/myfda/myfda.jar --spring.config.location=file:/opt/config/application.properties
