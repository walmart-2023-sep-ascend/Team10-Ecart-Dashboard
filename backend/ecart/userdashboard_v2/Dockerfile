FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} userdashboard.jar
EXPOSE 9903
ENTRYPOINT ["java","-jar","/userdashboard.jar"]