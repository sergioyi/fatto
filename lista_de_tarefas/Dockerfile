FROM maven:3.8.6-eclipse-temurin-17 AS build 

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para a raiz do diretório /app dentro do contêiner
COPY pom.xml .

# Copia o diretório src para /app/src dentro do contêiner
COPY src ./src

# Executa o comando Maven para gerar o JAR
RUN mvn clean install -DskipTests

# A partir da imagem do OpenJDK para a imagem final
FROM openjdk:17-jdk-slim

# Define o caminho para o arquivo JAR
ARG JAR_FILE=target/*.jar

# Copia o JAR gerado pela etapa de build para a imagem final
COPY --from=build ${JAR_FILE} app.jar

# Expõe a porta que o aplicativo irá rodar
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT [ "java", "-jar", "app.jar" ]
