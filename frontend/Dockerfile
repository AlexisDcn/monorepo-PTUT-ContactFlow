# Utilisez une image de base Node.js plus récente
FROM node:18-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier package.json et package-lock.json
COPY package*.json ./

# Installer les dépendances
# RUN npm install vue3-apexcharts (j'ai essayé de faire ça mais ça ne change rien)
RUN npm install

# Copier le reste des fichiers de l'application
COPY . .

# Construire l'application pour la production
RUN npm run build

# Utiliser une image de base Nginx pour servir l'application
FROM nginx:1.21-alpine

# Copier les fichiers de build dans le répertoire par défaut de Nginx
COPY --from=0 /app/target/dist /usr/share/nginx/html

# Exposer le port sur lequel Nginx s'exécute
EXPOSE 80

# Définir la commande pour exécuter Nginx
CMD ["nginx", "-g", "daemon off;"]