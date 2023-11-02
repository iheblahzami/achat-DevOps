# Utilisez l'image OpenJDK 11 de DockerHub
FROM openjdk:11

# Exposez le port de votre application Spring Boot
EXPOSE 8090

# Copiez le jar de votre application dans le conteneur
COPY target/achat-1.0.jar /app/achat-1.0.jar

# Définissez le répertoire de travail
WORKDIR /app

# Commande pour exécuter votre application Spring Boot
CMD ["java", "-jar", "/achat-1.0.jar"]
