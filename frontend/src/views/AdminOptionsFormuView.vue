<template>
  <AdminHeader />
  <BoutonRetour />
  <v-container>
    <h1>Ajouter des options</h1>
    <OptionComponent/>
  </v-container>
</template>

<script setup>
import OptionComponent from "@/components/OptionComponent.vue";
import AdminHeader from "@/components/AdminHeader.vue";
import BoutonRetour from "@/components/BoutonRetour.vue";
</script>
