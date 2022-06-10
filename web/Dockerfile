FROM eclipse-temurin:17-jre-alpine
MAINTAINER Kyun <me@iamkyun.com>

ENV TZ="Asia/Shanghai"
ADD target/winter-dashboard-service.jar /usr/share/service.jar

EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/service.jar"]
