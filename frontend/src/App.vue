<template>
  <v-app>
    <!-- Contenu principal -->
    <v-main>
      <v-container fluid class="fill-height">
        <router-view />
      </v-container>
    </v-main>
    <FooterGeneral />
  </v-app>
</template>

<script setup>
import { RouterView } from 'vue-router';
import FooterGeneral from "@/components/FooterGeneral.vue";
</script>

<style scoped>

</style>
