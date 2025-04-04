<template>
  <v-app-bar app color="primary" dark>
    <div @click="goHome" style="cursor: pointer;">
      <img src="@/assets/logo-ISIS-horizontal-BLANC.png" alt="ISIS Logo" height="55">
    </div>
    <v-spacer></v-spacer>
    <p>Contact'Flow</p>
    <v-spacer></v-spacer>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function goHome() {
  router.push('/public/home');
}
</script>

<style scoped>
img {
  max-height: 55px;
  width: auto;
  padding: 8px 0;
}
p{
  font-weight: bold;
  font-size: 20px;
  font-family: 'Plus Jakarta Sans', sans-serif;
}
</style>
