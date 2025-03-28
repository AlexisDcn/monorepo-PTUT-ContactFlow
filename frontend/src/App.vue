<template>
  <v-app>
    <AppHeader />
    <v-main>
      <router-view />
    </v-main>
    <FooterGeneral />
  </v-app>
</template>

<script setup>
import AppHeader from '@/components/AppHeader.vue';
import FooterGeneral from "@/components/FooterGeneral.vue";
</script>
