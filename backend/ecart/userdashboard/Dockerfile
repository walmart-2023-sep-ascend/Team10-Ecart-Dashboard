FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} userdashboard.jar
EXPOSE 9902
ENTRYPOINT ["java","-jar","/userdashboard.jar"]