FROM openjdk:8-jdk-alpine
MAINTAINER https://github.com/SutanBahtiar
COPY target/dans-jwt-1.0.0-RELEASE.jar dans-jwt-1.0.0-RELEASE.jar
ENTRYPOINT ["java","-jar","/dans-jwt-1.0.0-RELEASE.jar"]