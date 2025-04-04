<template>
  <v-app-bar app color="primary" dark>
    <div @click="goHome" style="cursor: pointer;">
      <img src="@/assets/logo-ISIS-horizontal-BLANC.png" alt="ISIS Logo" height="55">
    </div>
    <v-spacer></v-spacer>

    <v-btn text to="/admin/home">Accueil Admin</v-btn>
    <v-btn text to="/admin/statistiques-generales">Liste prospects</v-btn>
    <v-btn text to="/admin/creation-formu">Création de formulaire</v-btn>
    <v-btn text to="/admin/visualisation-formu">Formulaires en cours</v-btn>
    <v-btn text to="/admin/ajouter-des-options">Options</v-btn>
    <v-btn text @click="logout">Se déconnecter</v-btn>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { getAuth, signOut } from 'firebase/auth';

const router = useRouter();

function logout() {
  const auth = getAuth();
  signOut(auth).then(() => {
    console.log('Déconnecté avec succès');
    router.push('/public/login'); // Redirige vers la page de connexion
  }).catch((error) => {
    console.error('Erreur lors de la déconnexion:', error);
  });
}

function goHome() {
  router.push('/public/home');
}
</script>

<style scoped>
img {
  max-height: 55px;
  width: auto;
  padding: 8px 0;
}
</style>
