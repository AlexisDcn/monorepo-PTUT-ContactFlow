<template>
  <div>
    <h2>Statistiques générales</h2>
    <ComponentFormulaire />
  </div>
</template>

<script setup>

import ComponentFormulaire from "@/components/ComponentFormulaire.vue";
</script>



<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>
