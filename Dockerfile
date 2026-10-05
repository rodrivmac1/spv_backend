# Etapa de compilación usando Maven y Java 21
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copiamos los archivos de configuración y dependencias de Maven
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente y compilamos el proyecto
COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución ligera con Java 21 JRE
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el JAR generado desde la etapa de compilación
COPY --from=build /app/target/spv-backend-0.0.1-SNAPSHOT.jar app.jar

# Puerto en el que escucha Spring Boot
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]