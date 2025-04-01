<script setup>

import StatsGen from "@/components/StatsGen.vue";
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";
import BoutonRetour from "@/components/BoutonRetour.vue";


</script>

<template>
  <AdminHeader />
  <BoutonRetour />
 <v-container>
   <StatsGen />
 </v-container>
</template>

<style scoped>

</style>
