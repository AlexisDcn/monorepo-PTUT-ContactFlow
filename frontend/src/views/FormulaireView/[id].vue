<template>
  <MainHeaderProspect />
  <BoutonRetour />
  <ProspectForm :idSalon="idSalon" :idForm="idForm" />
</template>

<script setup>
import ProspectForm from "/src/components/ProspectForm.vue";
import { useRoute } from "vue-router";
import BoutonRetour from "@/components/BoutonRetour.vue";
import MainHeaderProspect from "@/components/MainHeaderProspect.vue";
const route = useRoute();
const idSalon = route.params.idSalon;
const idForm = route.params.idForm;

console.log(route.params);
</script>
