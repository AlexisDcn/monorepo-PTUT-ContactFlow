<template>
  <AdminHeader />
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <v-container>
    <h1>Ajouter des options</h1>
    <OptionComponent/>
  </v-container>
</template>

<script setup>
import router from "@/router/index.js";
import OptionComponent from "@/components/OptionComponent.vue";
import AdminHeader from "@/components/AdminHeader.vue";
function boutonRetour() {
  router.back();
}
</script>
