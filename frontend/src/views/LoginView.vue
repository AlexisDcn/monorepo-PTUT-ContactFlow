<script setup>
import router from "@/router/index.js";
import MainHeader from "@/components/MainHeader.vue";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <MainHeader />
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
</template>

<style scoped>

</style>
