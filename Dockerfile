FROM maven
RUN apt-get update && apt-get install bzip2 sudo
ADD src/ /opt/unikitty/src/
ADD pom.xml /opt/unikitty/
ADD .bowerrc /opt/unikitty/
ADD *.js /opt/unikitty/
ADD *.json /opt/unikitty/

WORKDIR /opt/unikitty
RUN mvn package -DskipTests -Dmaven.test.skip=true

EXPOSE 8080

CMD java -jar /opt/unikitty/target/*.jar
