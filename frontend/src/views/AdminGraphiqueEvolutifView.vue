<template>
  <AdminHeader />
  <h1>Choisir son type de graphique</h1>

  <div>
    <button @click="afficherGraphique('salon')">Graphique Salon</button>
    <button @click="afficherGraphique('annees')">Graphique Années</button>
  </div>

  <div>
    <GraphiqueSalon v-if="graphiqueActif === 'salon'" />
    <GraphiqueAnnees v-if="graphiqueActif === 'annees'" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import GraphiqueAnnees from "@/components/GraphiqueAnnees.vue";
import AdminHeader from "@/components/AdminHeader.vue";
import GraphiqueSalon from "@/components/GraphiqueSalon.vue";

const graphiqueActif = ref(null);

const afficherGraphique = (type) => {
  graphiqueActif.value = type;
};
</script>
