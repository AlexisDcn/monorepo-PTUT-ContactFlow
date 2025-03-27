<script setup>

import HeaderAdmin from "@/components/HeaderAdmin.vue";
import StatsGen from "@/components/StatsGen.vue";
import router from "@/router/index.js";

function boutonRetour() {
  router.back();
}
</script>

<template>
 <v-container>
   <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
   <StatsGen />
 </v-container>
</template>

<style scoped>

</style>
