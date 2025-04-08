<script setup>



import VueApexCharts from "vue3-apexcharts";
import {onMounted, reactive, ref, watch} from "vue";
import doAjaxRequest from "@/util/util.js";
import router from "@/router/index.js";
import boutonRetour from "@/components/BoutonRetour.vue";

const dataRecup = reactive([]);
const anneeList = reactive([]);
const listSalon = reactive([]);
const selectedSalons = ref([]);

const chartOptions = {
  chart: { type: "bar" },
  series: [{ name: "Nombre de prospects", data: dataRecup }],
  xaxis: { categories: anneeList }
};
function getPersonneParAnnee() {
  doAjaxRequest('/rest/getPersParAnnee')
    .then((result) => {
      // Itération sur le dictionnaire de base (Salon : {Dico})
      for (const [clefDicoGen, dicoSecondaire] of Object.entries(result)) {
        console.log(clefDicoGen, dicoSecondaire);
        // Itération sur le dico contenant les valeurs
        for (const [keyNom, valeurNb] of Object.entries(dicoSecondaire)) {
          console.log(keyNom);
          dataRecup.push(valeurNb);
          anneeList.push(keyNom);
        }
      }
    })
    .catch((error) => alert(error.message))
}

onMounted(()=>
  getPersonneParAnnee()
)

</script>

<template>
  <v-container>
    <h1>Graphique évolutif par année</h1>
    <VueApexCharts :options="chartOptions" :series="chartOptions.series" />
  </v-container>

  <div>
    <v-checkbox
      @change="handleClick(salon.idSalon)"
      type="checkbox"
      v-for="salon in listSalon"
      :key="salon.idSalon"
      v-model="selectedSalons"
      :label="salon.nom"
      :value="salon.idSalon"
    />
  </div>

</template>

<style scoped>

</style>
