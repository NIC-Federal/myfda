FROM java
RUN mkdir /usr/share/unikitty
RUN mkdir /usr/share/unikitty/conf
COPY unikitty*jar /usr/share/unikitty/unikitty.jar
WORKDIR /usr/share/unikitty
CMD java -jar unikitty.jar