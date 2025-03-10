<template>
  <v-app-bar app color="1" dark>
    <!-- Logo -->
    <v-img
      src="@/assets/logo-ISIS-vertical-BLANC.svg"
      max-height="40"
      max-width="150"
      contain
      @click="$router.push('/')"
      style="cursor: pointer;"
    ></v-img>

    <v-spacer></v-spacer>

    <!-- Bouton Statistiques générales -->
    <v-btn text to="/admin">Statistiques générales</v-btn>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';
useRouter();
</script>
