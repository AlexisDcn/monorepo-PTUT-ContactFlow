<template>
  <v-container class="fill-height text-center">

    <v-row justify="center" align="center" class="mt-16">
      <v-col cols="4">
        <v-btn to="/graphique-evolutif" color="primary" block>Graphique évolutif</v-btn>
      </v-col>
      <v-col cols="4">
        <v-btn to="/statistiques-generales" color="primary" block>Statistiques générales</v-btn>
      </v-col>
      <v-col cols="4">
        <v-btn to="/ajouter-des-options" color="primary" block>Ajouter des options</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import HeaderAdmin from "@/components/HeaderAdmin.vue";
</script>

<style scoped>
.fill-height {
  min-height: calc(100vh - 64px); /* Ajuste la hauteur en tenant compte du footer */
  padding: 16px;
}
</style>
