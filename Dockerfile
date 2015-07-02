FROM maven
RUN apt-get update && apt-get install bzip2 sudo
ADD src/ /opt/myfda/src/
ADD app/ /opt/myfda/app/
ADD config/ /opt/myfda/config/
ADD public/ /opt/myfda/public/
ADD tests/ /opt/myfda/tests/
ADD vendor/ /opt/myfda/vendor/
ADD pom.xml /opt/myfda/
ADD .bowerrc /opt/myfda/
ADD *.js /opt/myfda/
ADD *.json /opt/myfda/
ADD .jshintrc /opt/myfda/
ADD .ember-cli /opt/myfda/


WORKDIR /opt/myfda
RUN mvn package -DskipTests -Dmaven.test.skip=true

VOLUME /opt/config

EXPOSE 8080

CMD java -jar /opt/myfda/target/*.jar --spring.config.location=file:/opt/config/application.properties
