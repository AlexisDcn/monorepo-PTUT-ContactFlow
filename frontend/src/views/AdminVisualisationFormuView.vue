<script setup>
import router from "@/router/index.js";
import AdminHeader from "@/components/AdminHeader.vue";
import QRCodeGenerator from "@/components/QrCode.vue";
import BoutonRetour from "@/components/BoutonRetour.vue";
import FormList from "@/components/FormList.vue";

</script>

<template>
  <AdminHeader />
  <BoutonRetour />
  <FormList />
  <QRCodeGenerator />

</template>

<style scoped>

</style>
