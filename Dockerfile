# ============================
# FASE 1: Build con Maven
# ============================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos POM y luego el código
COPY pom.xml .
COPY src ./src

# Copiamos también la carpeta firebase (para que quede en el jar y/o en el contexto)
COPY firebase ./firebase

# Compilamos y empaquetamos
RUN mvn clean package -DskipTests

# ============================
# FASE 2: Imagen liviana para runtime
# ============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/target/catalog-service-0.0.1-SNAPSHOT.jar app.jar

# Copiamos la carpeta firebase tal como la espera application.properties
COPY --from=build /app/firebase ./firebase

# Exponemos el puerto del microservicio
EXPOSE 8081

# Arrancamos la app
ENTRYPOINT ["java", "-jar", "app.jar"]
