<script setup>

import SalonModif from "@/components/Option/SalonModif.vue";
import FormationModif from "@/components/Option/FormationModif.vue";
</script>

<template>
  <div class="options-container">
    <SalonModif/>
    <FormationModif/>
  </div>
</template>

<style scoped>
.options-container {
  max-width: 900px;
  margin: 0 auto;
  padding: 2rem;
}
</style>
