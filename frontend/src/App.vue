<template>
  <v-app>
    <v-main>
      <router-view />
      <FooterGeneral />
    </v-main>
  </v-app>
</template>

<script setup>
import AdminHeader from '@/components/AdminHeader.vue';
import FooterGeneral from "@/components/FooterGeneral.vue";
</script>
