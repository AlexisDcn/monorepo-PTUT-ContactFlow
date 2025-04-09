<template>
  <AdminHeader />
  <BoutonRetour />
  <div class="boutons-container">
    <button
      @click="afficherGraphique('salon')"
      :class="{ active: graphiqueActif === 'salon' }"
      class="bouton-graphique">
      Graphique Salon
    </button>
    <button
      @click="afficherGraphique('annees')"
      :class="{ active: graphiqueActif === 'annees' }"
      class="bouton-graphique">
      Graphique Années
    </button>
    <button
      @click="afficherGraphique('ville')"
      :class="{ active: graphiqueActif === 'ville' }"
      class="bouton-graphique">
      Graphique Ville
    </button>
  </div>

  <div class="graphiques-container">
    <GraphiqueSalon v-if="graphiqueActif === 'salon'" />
    <GraphiqueAnnees v-if="graphiqueActif === 'annees'" />
    <GraphiqueVille v-if="graphiqueActif === 'ville'"/>
  </div>
</template>

<script setup>
import { ref } from "vue";
import GraphiqueAnnees from "@/components/GraphiqueAnnees.vue";
import AdminHeader from "@/components/AdminHeader.vue";
import GraphiqueSalon from "@/components/GraphiqueSalon.vue";
import GraphiqueVille from "@/components/GraphiqueVille.vue";
import BoutonRetour from "@/components/BoutonRetour.vue";

const graphiqueActif = ref(null);

const afficherGraphique = (type) => {
  graphiqueActif.value = type;
};
</script>

<style scoped>
.boutons-container {
  display: flex;
  gap: 1rem;
  margin: 2rem 0;
  justify-content: center;
  flex-wrap: wrap;
}

.bouton-graphique {
  padding: 0.8rem 1.5rem;
  background-color: #372b73;
  border: none;
  border-radius: 0.375rem;
  color: white;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.bouton-graphique:hover {
  background-color: #251c55;
  transform: translateY(-2px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
}

.bouton-graphique.active {
  background-color: #5a49b3;
  color: white;
}

.graphiques-container {
  margin-top: 2rem;
  padding: 1.5rem;
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  .boutons-container {
    flex-direction: column;
    gap: 0.75rem;
    padding: 0 1rem;
  }

  .bouton-graphique {
    width: 100%;
  }
}
</style>
