<template>
  <v-app class="app-container">
    <v-main class="main-content">
      <router-view />
      <div class="content-spacer"></div>
    </v-main>
    <FooterGeneral />
  </v-app>
</template>

<script setup>
import FooterGeneral from "@/components/FooterGeneral.vue";
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
.main-content {
  flex: 1;
  padding: 20px;
}
.content-spacer {
  min-height: 45vh;
}
</style>
