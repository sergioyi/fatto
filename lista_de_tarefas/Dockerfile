FROM maven:3.8.6-eclipse-temurin-17 AS build 
WORKDIR /app 
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

#ARG JAR_FILE=target/*.jar
# Verifica se o JAR foi gerado corretamente no diretório 'target'
COPY --from=build /app/target/lista_de_tarefas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
