<template>
  <AdminHeader />
  <div class="container">
    <div class="button-row">
      <div class="button-col">
        <router-link to="/graphique-evolutif" class="btn primary">Graphique évolutif</router-link>
      </div>
      <div class="button-col">
        <router-link to="/statistiques-generales" class="btn primary">Statistiques générales</router-link>
      </div>
      <div class="button-col">
        <router-link to="/ajouter-des-options" class="btn primary">Ajouter des options</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import AdminHeader from "@/components/AdminHeader.vue";
</script>

<style scoped>
.container {
  min-height: calc(100vh - 64px);
  padding: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.button-row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 64px;
  width: 100%;
}

.button-col {
  width: 33.33%;
  padding: 0 8px;
}

.btn {
  width: 80%;
  max-width: 300px;
  padding: 10px;
  margin: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  text-decoration: none;
}

.btn:hover {
  background-color: #0056b3;
  transform: scale(1.05);
}
</style>
