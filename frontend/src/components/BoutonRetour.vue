<script setup>
import router from "@/router/index.js";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <div class="button-container">
    <v-btn @click="boutonRetour" color="error" class="mb-4">Retour</v-btn>
  </div>
</template>

<style scoped>
.button-container {
  margin: 16px;
}

.mb-4{
  border-radius: 15px;
}

.mb-4:hover {
  transform: scale(1.02) !important;
  filter: brightness(0.9) !important;
}
</style>
