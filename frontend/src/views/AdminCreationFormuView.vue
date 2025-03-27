<script setup>
import router from "@/router/index.js";
import FormBuilder from '@/components/FormBuilder.vue';

function boutonRetour() {
  router.back();
}
</script>

<template>
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <FormBuilder />
</template>

<style scoped>

</style>
