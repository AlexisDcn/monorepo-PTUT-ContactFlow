<script setup>
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";
import QRCodeGenerator from "@/components/QrCode.vue";

function boutonRetour() {
  router.back();
}
</script>

<template>
  <AdminHeader />
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <QRCodeGenerator />

</template>

<style scoped>

</style>
