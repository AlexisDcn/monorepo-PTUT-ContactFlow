<template>
  <v-app>
    <!-- Barre de navigation -->
    <v-app-bar app color="primary" dark>
      <!-- Logo -->
      <v-img
        src="src/assets/logo-ISIS-vertical-BLANC.svg"
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

    <!-- Contenu principal -->
    <v-main>
      <v-container fluid class="fill-height">
        <router-view />
      </v-container>
    </v-main>

    <!-- Footer -->
    <v-footer app color="primary" class="white--text">
      <v-spacer></v-spacer>
      <div>&copy; {{ new Date().getFullYear() }} — Vuetify</div>
    </v-footer>
  </v-app>
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style scoped>
.fill-height {
  min-height: calc(100vh - 112px); /* Ajuste la hauteur en tenant compte de la barre de navigation et du footer */
  padding: 16px;
}
</style>
