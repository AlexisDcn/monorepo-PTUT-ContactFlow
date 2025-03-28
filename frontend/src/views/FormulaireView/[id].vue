<template>
  <AdminHeader />
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <ProspectForm :idSalon="idSalon" />
</template>

<script setup>
import ProspectForm from "/src/components/ProspectForm.vue";
// useRoute permet de récupérer la route
import { useRoute } from "vue-router";
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";
const route = useRoute();
const idSalon = route.params.idSalon;

function boutonRetour() {
  router.back();
}
</script>
