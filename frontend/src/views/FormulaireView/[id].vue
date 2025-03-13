<template>
  <ProspectForm :idSalon="idSalon" />
</template>

<script setup>
import ProspectForm from "/src/components/ProspectForm.vue";
// useRoute permet de récupérer la route
import { useRoute } from "vue-router";
const route = useRoute();
const idSalon = route.params.idSalon;
</script>
