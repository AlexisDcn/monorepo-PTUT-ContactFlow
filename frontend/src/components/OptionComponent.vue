<script setup>
import SalonModif from "@/components/Option/SalonModif.vue";
import FormationModif from "@/components/Option/FormationModif.vue";

</script>

<template>
  <div class="options-container">
    <h2 class="main-title">Options de configuration</h2>
    <SalonModif/>
    <FormationModif/>
  </div>
</template>

<style scoped>
@import '../assets/css/options.css';

.main-title {
  color: white;
  font-size: 1.5rem;
  margin-bottom: 3rem;
  text-align: center;
  font-weight: 600;
}
</style>
