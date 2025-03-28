<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
    <FooterGeneral />
  </v-app>
</template>

<script setup>
import AdminHeader from '@/components/AdminHeader.vue';
import FooterGeneral from "@/components/FooterGeneral.vue";
</script>
