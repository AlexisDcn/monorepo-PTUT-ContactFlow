<template>
  <v-app-bar app color="1" dark>
    <v-toolbar-title @click="goHome" style="cursor: pointer;">ISIS</v-toolbar-title>    <v-spacer></v-spacer>

    <v-btn text to="/admin">Accueil Admin</v-btn>
    <v-btn text to="/statistiques-generales">Statistiques</v-btn>
    <v-btn text to="/creation-formu">Création de formulaire</v-btn>
    <v-btn text to="/visualisation-formu">Formulaires en cours</v-btn>
    <v-btn text to="/ajouter-des-options">Options</v-btn>
    <v-btn text @click="logout">Se déconnecter</v-btn>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function logout() {
  // Logique de déconnexion
  console.log('Déconnexion...');
  // Rediriger vers la page de connexion ou une autre page après la déconnexion
  router.push('/login');
}
function goHome() {
  router.push('/');
}
</script>

<style scoped>
.v-toolbar-title {
  cursor: pointer;
}
</style>
