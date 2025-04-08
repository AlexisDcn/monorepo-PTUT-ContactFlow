<template>
  <AdminHeader />
  <BoutonRetour />
  <v-container>
    <OptionComponent/>
  </v-container>
</template>

<script setup>
import OptionComponent from "@/components/OptionComponent.vue";
import AdminHeader from "@/components/AdminHeader.vue";
import BoutonRetour from "@/components/BoutonRetour.vue";
</script>
