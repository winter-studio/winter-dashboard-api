FROM eclipse-temurin:17-jre-alpine
MAINTAINER Kyun <me@iamkyun.com>

ENV TZ="Asia/Shanghai"
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/share/service.jar"]
