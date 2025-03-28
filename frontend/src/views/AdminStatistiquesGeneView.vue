<script setup>

import StatsGen from "@/components/StatsGen.vue";
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <AdminHeader />
 <v-container>
   <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
   <StatsGen />
 </v-container>
</template>

<style scoped>

</style>
