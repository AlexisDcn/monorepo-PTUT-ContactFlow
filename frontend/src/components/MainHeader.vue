<template>
  <v-app-bar app color="1" dark>
    <v-toolbar-title @click="goHome" style="cursor: pointer;">ISIS</v-toolbar-title>    <v-spacer></v-spacer>

    <v-btn text @click="login">Se Connecter</v-btn>

  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function login() {
  // Logique de connexion
  console.log('Connexion...');
  // Rediriger vers la page de connexion ou une autre page après la déconnexion
  router.push('/admin');
}
function goHome() {
  router.push('/');
}
</script>

<style scoped>

</style>
