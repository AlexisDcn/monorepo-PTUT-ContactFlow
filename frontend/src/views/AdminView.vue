<template>
  <v-container class="fill-height">
    <HeaderAdmin />
    <StatsGen />
  </v-container>
</template>

<script setup>
import HeaderAdmin from "@/components/HeaderAdmin.vue";
import StatsGen from "@/components/StatsGen.vue";
</script>

<style scoped>
.fill-height {
  min-height: calc(100vh - 64px); /* Ajuste la hauteur en tenant compte du footer */
  padding: 16px;
}
</style>
