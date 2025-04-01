<script setup>
import router from "@/router/index.js";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <v-btn @click="boutonRetour" color="error" class="mb-4">Retour</v-btn>
</template>

<style scoped>
.mb-4:hover {
  transform: scale(1.02) !important;
  filter: brightness(0.9) !important;
}
</style>
