<template>
  <v-container class="fill-height">
    <FormulaireValide />
  </v-container>
</template>

<script setup>
import FormulaireValide from "@/components/FormulaireValide.vue";
</script>

<style scoped>
.fill-height {
  min-height: calc(100vh - 64px); /* Ajuste la hauteur en tenant compte du footer */
  padding: 16px;
}
</style>
