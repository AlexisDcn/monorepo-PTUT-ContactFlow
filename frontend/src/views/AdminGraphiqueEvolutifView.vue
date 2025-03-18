<template>
  <v-btn @click="boutonRetour" color="4" class="mb-4">Retour</v-btn>
  <v-container>
    <HeaderAdmin />
    <h1>Graphique évolutif</h1>
    <VueApexCharts :options="chartOptions" :series="chartOptions.series" />
  </v-container>
</template>

<script setup>
import HeaderAdmin from "@/components/HeaderAdmin.vue";
import VueApexCharts from "vue3-apexcharts";
import {reactive} from "vue";
import router from "@/router/index.js";

const dataRecup = reactive([]);

const chartOptions = {
  chart: { type: "bar" },
  series: [{ name: "Ventes", data: dataRecup }]
};

function getPersonneParSalon(){
  doAjaxRequest('/rest/formulairesActifs')
    .then((result) => {
      console.log(result)
      for (let elmnt of result.formulaire){
        listeForm.push(elmnt)
      }
    })
    .catch((error) => alert(error.message))
}

function getSalon(){

}

function boutonRetour() {
  router.back();
}
</script>
