<script setup>
import ChoixFormu from "@/components/ChoixFormu.vue";
import MainHeader from "@/components/MainHeader.vue";
</script>

<template>
  <div class="container">
    <MainHeader />
    <ChoixFormu />
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin: 40px auto 0;
  padding: 0 20px;
}
</style>
