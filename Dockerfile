# Utilisez l'image OpenJDK 11 de DockerHub
FROM openjdk:11

WORKDIR /app



# Copiez le jar de votre application dans le conteneur
COPY target/achat-1.0.jar /app/achat-1.0.jar


EXPOSE 8089

# Commande pour exécuter votre application Spring Boot
CMD ["java", "-jar", "achat-1.0.jar"]
