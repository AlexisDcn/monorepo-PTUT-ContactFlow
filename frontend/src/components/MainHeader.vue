<template>
  <v-app-bar app color="primary" dark>
    <div @click="goHome" style="cursor: pointer;">
      <img src="@/assets/logo-ISIS-horizontal-BLANC.png" alt="ISIS Logo" height="55">
    </div>
    <v-spacer></v-spacer>

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
img {
  max-height: 55px;
  width: auto;
  padding: 8px 0;
}
</style>
