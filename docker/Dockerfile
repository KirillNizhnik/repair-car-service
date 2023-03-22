FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} repair-car-service.jar
ENTRYPOINT ["java","-jar","repair-car-service.jar"]