#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
VOLUME /tmp
ARG JAR_FILE=target/user-management-service.jar
#COPY ${JAR_FILE} user-management-service.jar
ADD ${JAR_FILE} user-management-service.jar
ENTRYPOINT ["java","-jar","/user-management-service.jar"]
#EXPOSE 2222
EXPOSE 8082