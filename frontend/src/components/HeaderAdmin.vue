<template>
  <v-app-bar app color="1" class="custom-header white--text">
    <!-- Logo -->
    <v-img
      src="src/assets/logo-ISIS-horizontal-BLANC.png"
      max-height="70"
      max-width="150"
      contain
      @click="$router.push('/')"
      style="cursor: pointer;"
    ></v-img>

    <v-spacer></v-spacer>

    <!-- Bouton Statistiques générales -->
    <v-btn class="StatsGen" to="/admin">Statistiques générales</v-btn>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';
useRouter();
</script>

<style scoped>
.custom-header {
  height: 80px !important;
  display: flex;
  align-items: center;
}
.StatsGen {
  position: absolute;
  left: 80%;
  top: 30%;
}

</style>
