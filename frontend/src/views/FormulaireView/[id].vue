<template>
  <MainHeaderProspect />
  <BoutonRetour />
  <ProspectForm :idSalon="idSalon" />
</template>

<script setup>
import ProspectForm from "/src/components/ProspectForm.vue";
import { useRoute } from "vue-router";
import BoutonRetour from "@/components/BoutonRetour.vue";
import MainHeaderProspect from "@/components/MainHeaderProspect.vue";
const route = useRoute();
const idSalon = route.params.idSalon;
</script>
