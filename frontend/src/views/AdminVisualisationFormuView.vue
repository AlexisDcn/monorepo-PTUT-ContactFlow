<script setup>
import router from "@/router/index.js";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>

</template>

<style scoped>

</style>
