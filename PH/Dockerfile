FROM openjdk:21-slim


# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app


# Copiar el JAR generado en la carpeta target
COPY target/PH-0.0.1-SNAPSHOT.jar app.jar


# Exponer el puerto en el que corre la aplicación
EXPOSE 8080


# Usar ENTRYPOINT en lugar de CMD para mejor control del proceso
ENTRYPOINT ["java", "-jar", "app.jar"]