# Utilisez une image de base OpenJDK 21
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/*.jar app.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8989

# Définir la commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]