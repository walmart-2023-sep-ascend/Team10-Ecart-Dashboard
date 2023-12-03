FROM openjdk:17
EXPOSE 9191
ADD target/apigateway.jar apigateway.jar
ENTRYPOINT ["java","-jar","/apigateway.jar"]