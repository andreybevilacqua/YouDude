FROM openjdk:11-slim

ENV JAVA_OPTS="-Xmx256m"

EXPOSE 8080

ADD target/*.jar /app.jar

CMD echo "YouDude application starting..." && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
