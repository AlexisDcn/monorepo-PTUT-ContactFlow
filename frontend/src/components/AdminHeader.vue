<template>
  <v-app-bar app color="primary" dark>
    <div @click="goHome" style="cursor: pointer;">
      <img src="@/assets/logo-ISIS-horizontal-BLANC.png" alt="ISIS Logo" height="55">
    </div>
    <v-spacer></v-spacer>

    <v-btn text to="/admin">Accueil Admin</v-btn>
    <v-btn text to="/graphique-evolutif">Graphiques</v-btn>
    <v-btn text to="/statistiques-generales">Prospects</v-btn>
    <v-btn text to="/creation-formu">Création de formulaire</v-btn>
    <v-btn text to="/visualisation-formu">Formulaires</v-btn>
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
  router.push('/');
}
function goHome() {
  router.push('/');
}
</script>

<style scoped>
img {
  max-height: 55px;
  width: auto;
  padding: 8px 0;
}
</style>
