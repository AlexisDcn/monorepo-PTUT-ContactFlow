<template>
  <v-app-bar app color="primary" dark>
    <v-toolbar-title>Contact’Flow</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn text to="/">Home</v-btn>
    <v-btn text to="/admin">Admin</v-btn>
  </v-app-bar>
  <v-main>
    <router-view />
  </v-main>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000;
  padding: 1rem;
  line-height: 1.5;
  max-height: 100vh;
}

nav a {
  color: black;
  text-decoration: none;
}

nav a:hover {
  color: gray;
}

nav a.router-link-exact-active {
  color: black;
  background-color: gray;
}
</style>
