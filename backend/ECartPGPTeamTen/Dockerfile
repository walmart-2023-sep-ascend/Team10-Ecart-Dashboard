FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ECartapp.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/ECartapp.jar"]