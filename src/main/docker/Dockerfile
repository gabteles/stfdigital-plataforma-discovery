FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD discovery.jar app.jar
ADD truststore.jks truststores/truststore.jks
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]