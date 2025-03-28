<script setup>
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <AdminHeader />
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>

</template>

<style scoped>

</style>
